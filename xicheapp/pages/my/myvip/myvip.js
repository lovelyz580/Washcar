// pages/my/myvip/myvip.js
//应用实例
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  // 查询会员
  selectvip: function(e) {

    var that = this
    var importantUserid = wx.getStorageSync("user").userId;
    app.util.request({
      url: 'washImportant/selectByCondition',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        importantUserid: importantUserid,
        pagenumber: -1
      },
      success: function(res) {
        console.log(res)
        if (res.data.count == 1) {
          that.setData({
            important: res.data.data[0]
          })

        } else {
          wx.showModal({
            title: '提示',
            content: '您未购买会员套餐,请在首页会员召集令中购买会员套餐',
          })
          wx.switchTab({
            url: '../../index/index',
          })

        }
        console.log(that.data.important)
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
  // 去洗车
  toorder: function(e) {
    wx.navigateTo({
      url: '../vipindex/vipindex',
    })
  },

  // 支付
  gotopay: function() {
    console.log("付款")
    var that = this
    var important = that.data.important
    var userId = important.importantCreatebyuserid
    var money = important.importantMark
    var orderId = ""
    var couponId = ""
    var importantId = important.importantId
    app.util.request({
      url: 'wechatpay/getPrepayId',
      method: 'post',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        userId: userId,
        orderId: orderId,
        couponId: couponId,
        importantId: importantId,
        money: money,
        tradeType: "JSAPI"
      },
      success: function(res) {
        console.log(res)
        that.setData({
          prepayId: res.data.msg
        })
        app.util.request({
          url: 'wechatpay/pay',
          method: 'post',
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: {
            prepayId: that.data.prepayId
          },
          success: function(res) {
            // console.log(res)
            var date = res.data.msg
            var front1 = date.indexOf('appId='); // 设置参数字符串开始的位置
            var front2 = date.indexOf('&nonceStr='); // 设置参数字符串开始的位置
            var front3 = date.indexOf('&paySign='); // 设置参数字符串开始的位置
            var length = date.length; // 获取url的长度
            var appId = date.substr(front1 + 6, front2 - front1 - 6); // 取出参数字符串 这里会获得类似"appId=1"这样的字符串	
            var nonceStr = date.substr(front2 + 10, front3 - front2 - 10);
            var paySign = date.substr(front3 + 9, length - front2 - 9);
            // console.log(paySign)
            wx.requestPayment({
              'appId': appId,
              'timeStamp': '1414587457',
              'nonceStr': nonceStr,
              'package': "prepay_id=" + that.data.prepayId,
              'signType': 'MD5',
              'paySign': paySign,
              'success': function(paymentRes) {
                console.log(paymentRes)
                wx.switchTab({
                  url: '../personal/personal',
                })
              },
              'fail': function(error) {
                console.log(error)
              }
            })
          }
        })
      }
    });
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.selectvip();
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