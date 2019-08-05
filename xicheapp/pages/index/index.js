var app = getApp();
Page({
  data: {
    systemInfo: {},
    phonetype: null,
    cartypes: [],
    _index: 1,
    index: 1,
    url: app.util.url(""),
    vedio: {
      title: '介绍洗车',
      url: 'http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400',
      img: '/img/family/banner.jpg',
    },
    parameter: [{
      id: 1,
      name: '先生',
      checked: true
    }, {
      id: 2,
      name: '女士'
    }],
    cleantypes: [],
    positionStatus: false,
    name: null,
    phone: null,
    date: "2019-06-06",
    starttime: "09:00",
    endtime: "17:00",
    onclear: null,
    ontype: null,
    expense: 0,
    usersex: "先生",

  },
  // 获取车辆定位
  getPosition: function() {
    var _this = this;
    wx.getSetting({
      success: (res) => {
        if (res.authSetting['scope.userLocation'] != undefined && res.authSetting['scope.userLocation'] != true) {
          //未授权
          wx.showModal({
            title: '请求授权当前位置',
            content: '需要获取您的地理位置，请确认授权',
            success: function(res) {
              if (res.cancel) {
                //取消授权
                wx.showToast({
                  title: '拒绝授权',
                  icon: 'none',
                  duration: 1000
                })
              } else if (res.confirm) {
                //确定授权，通过wx.openSetting发起授权请求
                wx.openSetting({
                  success: function(res) {
                    if (res.authSetting["scope.userLocation"] == true) {
                      wx.showToast({
                        title: '授权成功',
                        icon: 'success',
                        duration: 1000
                      })
                      //再次授权，调用wx.getLocation的API
                      _this.geo();
                    } else {
                      wx.showToast({
                        title: '授权失败',
                        icon: 'none',
                        duration: 1000
                      })
                    }
                  }
                })
              }
            }
          })
        } else if (res.authSetting['scope.userLocation'] == undefined) {
          //用户首次进入页面,调用wx.getLocation的API
          _this.geo();
        } else {
          console.log('授权成功')
          //调用wx.getLocation的API
          _this.geo();

        }

      }

    })
  },
  // 定位
  geo: function() {
    var that = this;
    wx.chooseLocation({
      success: function(res) {
        that.setData({
          address: res.address,
          latitude: res.latitude,
          longitude: res.longitude,
          positionStatus: true
        })
      }
    })
  },


  // 获取姓名
  getname: function(e) {
    var that = this;
    var name = e.detail.value;
    that.setData({
      name: name
    })
    console.log(that.data.name)
  },
  // 性别选择器
  parameterTap: function(e) {
    var that = this;
    var usersex = e.detail.value;
    that.setData({
      usersex: usersex
    })
    console.log(that.data.usersex)
  },
  // 获取电话
  getphone: function(e) {
    var that = this;
    var phone = e.detail.value; //验证电话
    var isPhone = /^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/; //手机号码
    if (!isPhone.test(phone)) {
      wx.showToast({
        title: '请输入正确的手机号码',
        icon: "none"
      })
      that.setData({
        phone: null
      })
      return;
    }
    that.setData({
      phone: phone
    })
    console.log(that.data.phone)
  },
  //日期选择器：
  bindDateChange: function(e) {
    var that = this;
    that.setData({
      date: e.detail.value
    })
    console.log("洗车日期");
    console.log(that.data.date);

  },
  //开始时间选择器：
  bindTimeChange: function(e) {
    var that = this;
    that.setData({
      starttime: e.detail.value
    })
    console.log("预约开始时间")
    console.log(that.data.starttime)
  },
  //结束时间选择器：
  bindTimeChange1: function(e) {
    var that = this;
    var endtime = e.detail.value;
    var starttime = that.data.starttime;
    if (endtime < starttime) {
      wx.showToast({
        title: '结束时间不得小于开始时间',
        icon: "none"
      })
      return;
    } else {
      this.setData({
        endtime: endtime
      })
    }

    console.log("预约结束时间")
    console.log(that.data.endtime)
  },




  // 获取车辆类型
  radiocar: function(e) {
    var that = this;
    that.islogin();
    var index = e.currentTarget.dataset.index; //获取当前点击的下标
    var cartypes = that.data.cartypes; //选项集合
    var ontype = cartypes[index] //获取选中的对象
    if (cartypes[index].checked) //如果点击的当前已选中则返回
    {
      ontype = null;
      cartypes[index].checked = false;
      that.setData({
        cartypes: cartypes,
        ontype: ontype,

      });
      //当前选中套餐
      console.log("当前选中套餐");
      console.log(that.data.ontype);
      return; //如果点击的当前已选中则返回
    }
    cartypes.forEach(item => {
      item.checked = false
    })
    cartypes[index].checked = true; //改变当前选中的checked值
    that.setData({
      cartypes: cartypes,
      ontype: ontype
    });
    var id = that.data.ontype.rankId
    wx.navigateTo({
      url: '../indexto/indexto?id=' + id,
    })
    cartypes[index].checked = false; //改变当前选中的checked值
    that.setData({
      cartypes: cartypes,
      ontype: ontype
    });

  },

  newwasher: function(e) {
    console.log("会员招募")
    wx.navigateTo({
      url: '../washer/washer',
    })
  },
  // 成为会员
  newvip: function(e) {
    this.islogin();
    wx.navigateTo({
      url: '../newvip/newvip',
    })
  },
  // 新用户收单体验
  newdetail: function(e) {
    wx.navigateTo({
      url: '../newdetail/newdetail',
    })
  },
  // 更多视频
  morevideo: function() {
    wx.showModal({
      title: '提示',
      content: '敬请期待',
    })
  },

  // 点击cover播放，其它视频结束
  videoPlay: function(e) {
    var _index = e.currentTarget.id
    this.setData({
      _index: _index
    })
    //停止正在播放的视频
    var videoContextPrev = wx.createVideoContext(this.data._index)
    videoContextPrev.stop();

    setTimeout(function() {
      //将点击视频进行播放
      var videoContext = wx.createVideoContext(_index)
      videoContext.play();
    }, 500)
  },


  // 查看用户是否登录
  islogin: function() {
    var that = this
    var openid = wx.getStorageSync("openid")
    if (openid == "" || openid == null) {
      wx.redirectTo({
        url: '../login/login',
      })
    } else {
      app.util.request({
        url: 'washUser/selectByCondition',
        header: {
          "Content-Type": "application/json;charset=UTF-8"
        },
        method: "POST",
        data: {
          userOpenid: openid,
          pagenumber: -1
        },
        success: function(res) {
          console.log(res)
          wx.setStorageSync('user', res.data.data[0])
        }
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this

    var timestamp = Date.parse(new Date());
    var date = new Date(timestamp);
    //获取年份  
    var Y = date.getFullYear();
    //获取月份  
    var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1);
    //获取当日日期 
    var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
    that.setData({
      date: Y + "-" + M + "-" + D
    })
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          systemInfo: res,
        })
        if (res.platform == "devtools") {
          that.setData({
            phonetype: "PC"
          })
        } else if (res.platform == "ios") {
          that.setData({
            phonetype: "IOS"
          })
        } else if (res.platform == "android") {
          that.setData({
            phonetype: "Android"
          })
        }
        console.log(that.data.phonetype)
      }

    })

    // 查询车辆类型
    app.util.request({
      url: 'washRank/selectByCondition',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        rankIsdel: 1,
        pagesize: ""
      },
      success: function(res) {
        // console.log(res)
        var cartypes = res.data.data;
        for (var a = 0; a < cartypes.length; a++) {
          cartypes[a].checked = false
        }
        that.setData({
          cartypes: cartypes
        })
        var id = that.data.cartypes[0].rankId
        // 查询清洁类型
        // that.cleantype(id)
        console.log(that.data.cartypes)
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