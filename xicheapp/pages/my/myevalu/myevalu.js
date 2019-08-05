// pages/my/mymake/mymake.js
//order.js
//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTap: 0,
    pagenumber: 2,
    pagesize: 3,

  },
  // 事件
  commentOrder: function (e) {
    console.log(e)
    var orderid = e.target.dataset.id
    wx.navigateTo({
      url: '/pages/my/commentOrder/commentOrder?orderid=' + orderid
    })
  },
  // 订单详情
  orderdetail: function (e) {
    var orderid = e.currentTarget.dataset.id
    console.log(orderid)
    wx.navigateTo({
      url: '../../orderdetail/orderdetail?orderid=' + orderid
    })
  },
  // 查询订单
  selectorder: function () {
    var that = this
    var userid = wx.getStorageSync("user").userId;
    console.log(userid)
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagesize: that.data.pagesize,
        pagenumber: 1,
        orderServiceid: wx.getStorageSync("user").userId,
        orderStatic:"YWC"
      },
      success: function (res) {
        console.log(res)
        if (res.data.count < 1) {
          wx.showModal({
            content: "暂无数据",
            showCancel: false,
            success: function (res) {
              wx.switchTab({
                url: '../../my/my',
              })
            }
          });
        } else {
          that.setData({
            orderlist: res.data.data
          })
        }



      },
      fail: function () {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function (res) {
            if (res.confirm) {

            }
          }
        });
      }
    });
  },
  // 取消订单
  deleteorder: function (e) {
    var that = this;
    var orderid = e.currentTarget.dataset.id
    console.log(orderid)
    wx.showModal({
      title: '确认取消',
      content: '确认取消订单吗？',
      cancelText: "取消",
      confirmText: "确认",
      success: function (res) {
        if (res.confirm) {
          that.cancel(orderid);
        } else {
          return;
        }
      }
    })
  },

  // 取消订单的方法
  cancel: function (id) {
    var that = this
    app.util.request({
      url: 'washOrder/update',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        orderId: id,
        orderStatic: "YQX",
      },
      success: function (res) {
        console.log("取消结果")
        that.onLoad();
      },
      fail: function () {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function (res) {
            if (res.confirm) {

            }
          }
        });
      }
    });
  },

  // 加载更多
  moreinfo: function (orderStatic) {
    var that = this
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagesize: that.data.pagesize,
        pagenumber: that.data.pagenumber,
        orderServiceid: wx.getStorageSync("user").userId,
      },
      success: function (res) {
        console.log(res)
        if (res.data.data.length == 0) {
          wx.showModal({
            content: "已加载全部内容",
            showCancel: false,
            success: function (res) {
              return;
            }
          })
        } else {
          that.setData({
            pagenumber: that.data.pagenumber + 1
          })
          var list = that.data.orderlist
          var lists = [...list, ...res.data.data]
          that.setData({
            orderlist: lists,
          })
        }
      },
      fail: function () {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function (res) { }
        });
      }
    });

  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    that.selectorder();


  },



  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    var that = this
    that.onLoad();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var that = this;
    that.moreinfo()
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})