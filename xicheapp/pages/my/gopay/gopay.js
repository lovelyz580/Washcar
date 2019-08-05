// pages/my/gopay/gopay.js
//应用实例
const app = getApp();
Page({
  /**
   * 页面的初始数据
   */
  data: {
    couponmoney: 0,
    couponuses: [],
    index: 0,//默认显示位置

  },
  bindPicker:function (e){
    console.log(e)
    var that = this;
    var couponuses = that.data.couponuses
    var index = e.detail.value;
    var onecouponu = couponuses[index]
    that.setData({
      onecouponu: onecouponu
    })
    console.log(that.data.onecouponu)
    var orderAllmoney = that.data.orderone.orderAllmoney;
    if (onecouponu.couponuseRemoney > orderAllmoney){
     wx.showModal({
       title: '提示',
       content: '不满足使用条件',
     })
     that.setData({
       onecouponu:null
     })
    }else{
      var couponmoney = onecouponu.couponuseMoney//优惠金额
      var paymoney = that.data.paymoney - couponmoney
      that.setData({
        couponmoney: couponmoney,
        paymoney: paymoney
      })
    }

  },
  // 付款
  gotopay: function(e) {
    var that = this
    var userId = wx.getStorageSync("user").userId
    console.log(userId)
    var paymoney = that.data.paymoney
    var orderId = that.data.orderone.orderId
    var couponId = ""
    // 优惠券id
    if (that.data.onecouponu != null) {
      couponId = that.data.onecouponu.couponuseId
    }
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
        money: paymoney,
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



//  查询已有的优惠券
selectcoupon:function (userid){
  var that = this;
  app.util.request({
    url: 'washCouponuse/selectByCondition',
    header: {
      "Content-Type": "application/json;charset=UTF-8"
    },
    method: "POST",
    data: {
      pagenumber: -1,
      couponuseUserid: userid,
    },
    success: function (res) {
      console.log(res)
      that.setData({
        couponuses:res.data.data
      })
      console.log(that.data.couponuses)
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

  // 查询订单详情方法
  orderdetail: function(orderId) {
    var that = this;
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
        console.log(res)
        var paymoney = res.data.data[0].orderAllmoney - that.data.couponmoney
        that.setData({
          orderone: res.data.data[0],
          paymoney: paymoney
        })
        console.log(that.data.orderone)
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var orderId = options.orderId;
    // var orderId = "JDa653b32f-0ce3-494f-b9a6-2b5fb7a5c4be";
    var userid = wx.getStorageSync('user').userId
    console.log(orderId);
    //  查询订单详情
    that.orderdetail(orderId);
    // 查询已有的优惠券
    that.selectcoupon(userid);
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