//index.js
//获取应用实例
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    flag: 0,
    noteMaxLen: 100, // 最多放多少字
    info: "",
    noteNowLen: 0, //备注当前字数
  },
  // 监听字数
  bindTextAreaChange: function(e) {
    var that = this
    var value = e.detail.value,
      len = parseInt(value.length);
    if (len > that.data.noteMaxLen)
      return;
    that.setData({
      info: value,
      noteNowLen: len
    })

  },
  // 提交清空当前值
  bindSubmit: function() {
    var that = this;
    var flag = that.data.flag //评分
    var info = that.data.info //评价
    console.log(flag)
    console.log(info)
    // 添加评价
    app.util.request({
      url: 'washEvaluate/insert',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        evaluateIsgood: that.data.flag,
        evaluateContent: that.data.info,
        evaluateOrderid: that.data.order.orderId,
        evaluateCreatebyid: that.data.order.orderUserid,
        evaluateTouserid: that.data.order.orderServiceid,
        evaluateTouserid: that.data.order.orderServiceid,
      },
      success: function(res) {
        console.log(res)
        if (res.data.count == 1) {
          wx.showModal({
            title: '提示',
            content: '评价成功',
            success: function () {
              that.setData({
                info: '',
                noteNowLen: 0,
                flag: 0
              })
              wx.switchTab({
                url: '../../my/my',
              })
            }
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
    return


  },
  // 星星改变
  changeColor1: function() {
    var that = this;
    that.setData({
      flag: 1
    });
  },
  changeColor2: function() {
    var that = this;
    that.setData({
      flag: 2
    });
  },
  changeColor3: function() {
    var that = this;
    that.setData({
      flag: 3
    });
  },
  changeColor4: function() {
    var that = this;
    that.setData({
      flag: 4
    });
  },
  changeColor5: function() {
    var that = this;
    that.setData({
      flag: 5
    });
  },
  // 星星改变

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    that.setData({
      orderid: options.orderid
    })
    console.log(that.data.orderid)
    var that = this
    // 查询订单详情
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        orderId: that.data.orderid

      },
      success: function(res) {
        console.log(res)
        that.setData({
          order: res.data.data[0],
          orderiseva: res.data.data[0].orderIseva,
        })
        if (that.data.orderiseva == 1) {
          // 查询评价
          that.selectevalute(that.data.orderid)
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
  // 查询评价
  selectevalute: function(orderid) {
    var that = this
    console.log(orderid)
    app.util.request({
      url: 'washEvaluate/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        evaluateOrderid: orderid

      },
      success: function(res) {
        console.log(res)
        that.setData({
          info: res.data.data[0].evaluateContent,
          flag: res.data.data[0].evaluateIsgood,

        })
      }
    })

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

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})