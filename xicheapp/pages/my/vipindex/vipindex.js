var app = getApp();
Page({
  data: {
    systemInfo: {},
    phonetype: null,
    cartypes: [],
    parameter: [{
      id: 1,
      name: '先生',
      checked: true
    }, {
      id: 2,
      name: '女士'
    }],
    cleantypes: [{
      "feeid":"importanttypePtusenum",
        "feeName": "普通洗车",
        "residuenum": "200"
      }, {
        "feeid": "importanttypeFdusenum",
        "feeName": "洗发动机舱",
        "residuenum": "100"
      }, {
        "feeid": "importanttypeKtusenum",
        "feeName": "空调清洗",
        "residuenum": "50"
      },
      {
        "feeid": "importanttypeNsusenum",
        "feeName": "内饰精洗",
        "residuenum": "10"
      },
    ],
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
    wx.getLocation({
      type: 'wgs84',
      success(res) {
        console.log(res)
        const latitude = res.latitude
        const longitude = res.longitude
        const speed = res.speed
        const accuracy = res.accuracy
      }
    })
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

  // 改变清洁类型
  radioclear: function(e) {
    var that = this;
    var index = e.currentTarget.dataset.index; //获取当前点击的下标
    var cleantypes = that.data.cleantypes; //选项集合
    var onclear = cleantypes[index];
    if (onclear.residuenum<1){
      wx.showModal({
        title: '提示',
        content: '暂无使用次数,请充值!',
      })
      return;
    }
    if (cleantypes[index].checked) {
      onclear = null;
      cleantypes[index].checked = false;
      that.setData({
        cleantypes: cleantypes,
        onclear: onclear,
        expense: 0
      });
      //当前选中套餐
      console.log("当前选中套餐");
      console.log(that.data.onclear);
      return; //如果点击的当前已选中则返回
    }
    cleantypes.forEach(item => {
      item.checked = false
    })
    cleantypes[index].checked = true; //改变当前选中的checked值
    console.log("优惠前价格")
    that.setData({
      cleantypes: cleantypes,
      onclear: onclear,
      expense: 0,
    });
    //当前选中套餐
    console.log("当前选中套餐");
    console.log(that.data.onclear);
  },
  // 提交
  confirm: function() {
    var openid = wx.getStorageSync("openid")
    if (openid == "") {
      wx.showToast({
        title: '请先登录',
        icon: "none"
      })
      wx.switchTab({
        url: '../my/my',
      })
      return
    }
    var that = this;
    var clear = that.data.onclear //选中清洁类型
    var name = that.data.name; //姓名
    var usersex = that.data.usersex; //性别
    var phone = that.data.phone; //电话
    if (clear == null) {
      wx.showToast({
        title: '请选择清洁类型',
        icon: "none"
      })
      return;
    }
    if (!that.data.positionStatus) {
      wx.showToast({
        title: '请获取车辆位置',
        icon: "none"
      })
      return;
    }
    if (name == null) {
      wx.showToast({
        title: '请输入您的贵姓',
        icon: "none"
      })
      return;
    }

    if (phone == null) {
      wx.showToast({
        title: '请输入您的电话',
        icon: "none"
      })
      return;
    }
    var username = name + usersex;
    console.log(username) //姓名
    var starttime = that.data.date + "-" + that.data.starttime;
    var start_time = new Date(starttime.replace(/-/g, "/"))
    console.log(start_time);
    var endtime = that.data.date + "-" + that.data.endtime;
    var end_time = new Date(endtime.replace(/-/g, "/"))
    var userid = wx.getStorageSync("user").userId
    // 向数据库添加一个订单
    app.util.request({
      url: 'washOrder/insert',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        orderUserid: userid,
        orderNumber: that.data.orderNumber,
        orderUsername: username,
        orderUserphone: phone,
        orderAddress: that.data.address,
        orderStarttime: start_time,
        orderEndtime: end_time,
        orderFeeid: clear.feeid,
        orderFeename: clear.feeName,
        orderRankname:"会员",
        orderAllmoney: that.data.expense, //优惠前的价格
        orderPaymoney: that.data.expense, //优惠后的价格
        orderOther1: that.data.latitude,
        orderOther2: that.data.longitude,
        orderOther3: that.data.onclear.explain,
        userIsfirst:0,
      },
      success: function(res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showModal({
            content: "预约成功,系统正在为您派单",
            showCancel: false,
            success: function(res) {
              that.setData({
                address: null,
                phone: null,
                name: null,
                expense: 0
              })
              wx.switchTab({
                url: '../../index/index',
              })
            }
          });
        } else if (res.data.code == 220) {
          wx.showModal({
            content: "请勿重复下单",
            showCancel: false,
            success: function(res) {
              that.setData({
                address: null,
                phone: null,
                name: null,
                expense: 0
              })
              wx.switchTab({
                url: '../index/index',
              })
            }
          });
        }
      },
      fail: function() {
        wx.showModal({
          content: "发送请求失败,请重新点击",
          showCancel: false,
          success: function(res) {
            if (res.confirm) {

            }
          }
        });
      }
    });
  },

  // 查询用户是否是首单
  // 查询用户是否是首单
  selectuser: function () {
    var that = this
    var userId = wx.getStorageSync("user").userId
    app.util.request({
      url: 'washUser/select',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "GET",
      data: {
        userId: userId,

      },
      success: function (res) {
        console.log(res)
        var name = res.data.dataone.userRname
        name = name.substring(0, 1)
        var orderNumber = null;
        if (res.data.dataone.userRemark != null) {
          orderNumber = res.data.dataone.userRemark
        }
        that.setData({
          name: name,
          phone: res.data.dataone.userPhone,
          orderNumber: orderNumber,
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

  // 查询会员剩余次数
    // 查询会员剩余次数
    selectvip:function (e){
      var that = this
      var userId = wx.getStorageSync("user").userId
      app.util.request({
        url: 'washImportant/selectByCondition',
        header: {
          "Content-Type": "application/json;charset=UTF-8"
        },
        method: "POST",
        data: {
          importantUserid: userId,
          pagenumber:-1,

        },
        success: function (res) {
          console.log(res)
          var vipdata = res.data.data[0]
          console.log(vipdata)
          var a = that.data.cleantypes
          a[0].residuenum = vipdata.importanttypePtnum - vipdata.importanttypePtusenum;
          a[1].residuenum = vipdata.importanttypeFdnum - vipdata.importanttypeFdusenum;
          a[2].residuenum = vipdata.importanttypeKtnum - vipdata.importanttypeKtusenum;
          a[3].residuenum = vipdata.importanttypeNsnum - vipdata.importanttypeNsusenum;
         that.setData({
           cleantypes:a
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
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    var id = options.id
    console.log(id)
    that.selectuser();
    that.selectvip();

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