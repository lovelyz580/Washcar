const app = getApp();
Page({
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function () {
    var that = this;
    // 查看是否授权
    wx.getSetting({
      success: function (res) {
        if (res.authSetting['scope.userInfo']) {
          wx.getUserInfo({
            success: function (res) {
              //用户已经授权过
              console.log("您已授权");
            }
          });
        }
      }
    })
  },
  /*
   * 获取用户信息
   */
  bindGetUserInfo: function (cb) {
    var that = this;
    var userInfo = {};
    wx.login({
      success: res => {
        var app = getApp();
        var url = app.util.url("wechat/getuserinfo");
        wx.getUserInfo({
          success: info => {
            wx.request({
              data: {
                code: res.code,
                encryptedData: info.encryptedData,
                iv: info.iv,
              },
              url: url,
              success: res => {
                if (res.data.code == 200) {
                  userInfo = res.data.dataone;
                  wx.setStorageSync('userInfo', userInfo);
                  that.writedata(userInfo)
                }
                typeof cb == "function" && cb(userInfo);
              }
            })
          },
          fail: info => { }
        })
      }
    })
  },
  //写入数据库
  writedata: function (data) {
    console.log(data);
    app.util.request({
      url: 'washUser/insert',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        userName: data.nickName,
        userSex: data.gender,
        userOpenid: data.openId,
      },
      success: function (data) {
        console.log(data.data.dataone.userOpenid)
        var openid = data.data.dataone.userOpenid
        wx.setStorageSync("openid", openid)
        wx.setStorageSync("user", data.data.dataone)
        wx.switchTab({
          url: '../index/index',
        })

      }
    })
  }

})