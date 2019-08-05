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
  order_status: function(e) {
    var orderStatic = null;
    var that = this;
    var current = e.currentTarget.dataset.current
    that.setData({
      currentTap: current,
      orderlist: null

    })
    if (current == 1) { //代付款
      orderStatic = "WC"
    } else if (current == 2) { //待接单
      orderStatic = "TJ"
    } else if (current == 3) { //服务中
      orderStatic = "JD"
    } else if (current == 4) { //代评价
      orderStatic = "YFK"
    }
    that.selectorder(orderStatic)
  },
  // 去评价
  commentOrder: function(e) {
    console.log(e)
    var orderid = e.target.dataset.id
    wx.navigateTo({
      url: '/pages/my/commentOrder/commentOrder?orderid=' + orderid
    })
  },
  // 订单详情
  orderdetail: function(e) {
    var orderid = e.currentTarget.dataset.id
    console.log(orderid)
    wx.navigateTo({
      url: '../../orderdetail/orderdetail?orderid=' + orderid
    })
  },
  // 付款
  // 微信支付
  payment: function(e) {
    var that = this;
    var orderId = e.currentTarget.dataset.id
    // console.log(orderId)
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        orderId: orderId,
      },
      success: function(res) {
        var order = res.data.data[0]
        that.setData({
          order: order
        })
        // console.log(order)
        if (order.orderRankname == "会员") {
          wx.showModal({
            title: '提示',
            content: '确认已经洗完车吗？',
            cancelText: "取消",
            confirmText: "确认",
            success: function(res) {
              if (res.confirm) {
                that.updateorder(order);
              } else {
                return;
              }
            }
          })

        } else {
          wx.navigateTo({
            url: '../gopay/gopay?orderId=' + orderId,
          })
        }
        // console.log(that.data.order)
      },
      fail: function() {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function(res) {
            if (res.confirm) {

            }
          }
        });
      }
    });

  },

  // 会员订单更新
  updateorder: function(order) {
    var that = this;
    console.log("更新订单信息，添加会员使用记录")
    console.log(order)
    app.util.request({
      url: 'washOrder/payupdateorder',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        orderId: order.orderId,
        orderUserid: order.orderUserid,
        orderFeeid: order.orderFeeid,
      },
      success: function (res) {
         console.log(res);
         if(res.data.code==200){
           wx.showToast({
            title: "操作成功",
          icon: 'success',
          duration: 800,
             mask: true
           });
           that.onLoad();
         }else{
           wx.showToast({
             title: "操作失败",
             icon: 'none',
             duration: 800,
             mask: true
           });
           that.onLoad();
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

  // 查询订单
  selectorder: function(orderStatic) {
    var that = this
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagesize: that.data.pagesize,
        pagenumber: 1,
        orderUserid: wx.getStorageSync("user").userId,
        orderStatic: orderStatic,
      },
      success: function(res) {
        console.log(res)
        if (res.data.count < 1) {
          wx.showModal({
            content: "暂无数据",
            showCancel: false,
            success: function(res) {

            }
          });
        } else {
          that.setData({
            orderlist: res.data.data
          })
        }



      },
      fail: function() {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function(res) {
            if (res.confirm) {

            }
          }
        });
      }
    });
  },
  // 取消订单
  deleteorder: function(e) {
    var that = this;
    var orderid = e.currentTarget.dataset.id
    console.log(orderid)
    wx.showModal({
      title: '确认取消',
      content: '确认取消订单吗？',
      cancelText: "取消",
      confirmText: "确认",
      success: function(res) {
        if (res.confirm) {
          that.cancel(orderid);
        } else {
          return;
        }
      }
    })
  },

  // 取消订单的方法
  cancel: function(id) {
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
      success: function(res) {
        console.log("取消结果")
        that.onLoad();
      },
      fail: function() {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function(res) {
            if (res.confirm) {

            }
          }
        });
      }
    });
  },

  // 加载更多
  moreinfo: function(orderStatic) {
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
        orderUserid: wx.getStorageSync("user").userId,
        orderStatic: orderStatic,
      },

      success: function(res) {
        console.log(res)
        if (res.data.data.length == 0) {
          wx.showModal({
            content: "已加载全部内容",
            showCancel: false,
            success: function(res) {
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
      fail: function() {
        wx.showModal({
          content: "发送请求失败",
          showCancel: false,
          success: function(res) {}
        });
      }
    });

  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.selectorder(null);


  },



  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    that.onLoad();
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {
    var that = this;
    var current = that.data.currentTap
    var orderStatic = null
    if (current == 1) { //代付款
      orderStatic = "WC"
    } else if (current == 2) { //待接单
      orderStatic = "TJ"
    } else if (current == 3) { //服务中
      orderStatic = "JD"
    } else if (current == 4) { //代评价
      orderStatic = "YFK"
    }

    that.moreinfo(orderStatic)
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})