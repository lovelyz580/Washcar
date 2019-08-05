//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: null,
    userrole: "KH",
  },
  myvip:function (e){
    wx.navigateTo({
      url: './myvip/myvip',
    })
  },


  // 客户我的优惠券
  mycoupon: function(e) {
    wx.navigateTo({
      url: './coupon/coupon',
    })
  },
  // 客户我的预约
  mymake: function(e) {
    wx.navigateTo({
      url: './mymake/mymake',
    })
  },
  // 客户 我的评价记录
  mytoevalu:function (e){
    wx.navigateTo({
      url: './mytoevalu/mytoevalu',
    })
  },
  // 洗车人员 待洗订单
  myxcorder: function(e) {
    wx.navigateTo({
      url: './myxcorder/myxcorder',
    })
  },
  // 洗车人员  洗车记录
  myrecord: function(e) {
    wx.navigateTo({
      url: './myrecord/myrecord',
    })
  },
  
  // 洗车人员  评价记录
  myevalu: function(e) {
    wx.navigateTo({
      url: './myevalu/myevalu',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function() {
    var that = this
    var openid = wx.getStorageSync("openid")
    if (openid == "") {
      wx.showModal({
        content: "您未登录，请点击登录",
        showCancel: true,
        cancelText: "取消",
        confirmText: "登录",
        success: function(res) {
          console.log(res)
          if (res.confirm) {
            console.log("openid")
            wx.redirectTo({
              url: '../login/login',
            })
          } else {
            wx.switchTab({
              url: '../workhome/workhome',
            })
          }
        }
      })

    } else {
      var userInfo = wx.getStorageSync("userInfo");
      that.setData({
        userInfo: userInfo
      })
      that.selectuserinfo(userInfo.openId);

    }
  },
  selectuserinfo: function (openid){
    console.log(openid)
    var  that = this;
    app.util.request({
      url: 'washUser/selectByCondition',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        userOpenid: openid,
      },
      success: function (res) {
        console.log(res)
        var  user = res.data.data[0]
        that.setData({
          userrole: user.userRole
        })
 



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

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    this.onLoad();
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