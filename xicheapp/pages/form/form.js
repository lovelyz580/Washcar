//获取应用实例
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    upload_picture_listago: [],
    upload_picture_listyet: [],
    imgBoolean: true,
    imgsrcago:"",
    imgsrcyet:"",
    carcode:"",
    orderStatic:null,
    uploadago:false,
    uploadyet:false,
  },
  // 洗车前选择照片
  chooseImageago: function (e) {
      var that = this //获取上下文
    var upload_picture_listago = that.data.upload_picture_listago
    console.log(upload_picture_listago)
    if (upload_picture_listago.length >3) {
      wx.showModal({
        content: "最多只能上传4张图片",
        showCancel: false,
        success: function (res) {
          return
        }
      });
    } else{
      wx.chooseImage({
        count: 4,
        sizeType: ['compressed'],
        sourceType: ['album', 'camera'],
        success: function (res) {
          var tempFiles = res.tempFiles
          //把选择的图片 添加到集合里
          for (var i in tempFiles) {
            tempFiles[i]['upload_percent'] = 0
            tempFiles[i]['path_server'] = ''
            upload_picture_listago.push(tempFiles[i])
          }
          //显示
          that.setData({
            upload_picture_listago: upload_picture_listago,
          });
        }
      })
    }
  
    },
  // 洗车前删除图片
  deleteImgago: function (e) {
    var that = this
    let upload_picture_listago = this.data.upload_picture_listago;
    let index = e.currentTarget.dataset.index;
    upload_picture_listago.splice(index, 1);
    that.setData({
      upload_picture_listago: upload_picture_listago
    });
    console.log(that.data.upload_picture_listago)
  },
  // 洗车前的照片上传图片
  updateImgago: function () {
    var that = this
    console.log("洗车前的照片上传")
    var upload_picture_listago = that.data.upload_picture_listago
    console.log(upload_picture_listago)
    if (upload_picture_listago.length < 1) {
      wx.showModal({
        content: "请至少上传一张图片",
        showCancel: false,
        success: function (res) {
          return
        }
      });
    }
    var url = app.util.url("shangchuan/image");
    console.log(url)
    for (var j in upload_picture_listago) {
      if (upload_picture_listago[j]['upload_percent'] == 0) {　　　　　　 //调用函数
        //上传返回值
        var that = this;
        const upload_task = wx.uploadFile({
          // 模拟https
          url: url, //需要用HTTPS，同时在微信公众平台后台添加服务器地址  
          filePath: upload_picture_listago[j]['path'], //上传的文件本地地址    
          name: 'file',
          formData: {
            'num': j
          },
          //附近数据，这里为路径     
          success: function (res) {
            console.log(res)
            wx.showToast({
              title: '图片上传成功',
              icon: "none"
            })
            var jsonStr = res.data;
            jsonStr = jsonStr.replace(" ", "");
            if (typeof jsonStr != 'object') {
              jsonStr = jsonStr.replace(/\ufeff/g, ""); //重点
              var jj = JSON.parse(jsonStr);
              res.data = jj;
            }
            var imgsrcago = that.data.imgsrcago
            imgsrcago = imgsrcago + res.data.srcImg + ",";
            that.setData({
              imgsrcago: imgsrcago,
              uploadago:true
            })
            console.log("洗车前图片路径")
            console.log(that.data.imgsrcago)
          },
          fail: function (err) {
            console.log("上传服务器失败")
          } //请求失败
        })
      }
    }
  },

// 
//  洗车后的照片
// 

  // 洗车后选择照片
  chooseImageyet: function (e) {
    var that = this //获取上下文
    var upload_picture_listyet = that.data.upload_picture_listyet
    console.log(upload_picture_listyet)
    if (upload_picture_listyet.length > 3) {
      wx.showModal({
        content: "最多只能上传4张图片",
        showCancel: false,
        success: function (res) {
          return
        }
      });
    } else {
      wx.chooseImage({
        count: 4,
        sizeType: ['compressed'],
        sourceType: ['album', 'camera'],
        success: function (res) {
          var tempFiles = res.tempFiles
          //把选择的图片 添加到集合里
          for (var i in tempFiles) {
            tempFiles[i]['upload_percent'] = 0
            tempFiles[i]['path_server'] = ''
            upload_picture_listyet.push(tempFiles[i])
          }
          //显示
          that.setData({
            upload_picture_listyet: upload_picture_listyet,
          });
        }
      })
    }

  },
  // 洗车后删除图片
  deleteImgyet: function (e) {
    let upload_picture_listyet = this.data.upload_picture_listyet;
    let index = e.currentTarget.dataset.index;
    upload_picture_listyet.splice(index, 1);
    this.setData({
      upload_picture_listyet: upload_picture_listyet
    });
  },
  // 洗车后的照片上传图片
  updateImgyet: function () {
    console.log("洗车后的照片上传")
    var that = this
    var upload_picture_listyet = that.data.upload_picture_listyet
    console.log(upload_picture_listyet)
    if (upload_picture_listyet.length < 1) {
      wx.showModal({
        content: "请至少上传一张图片",
        showCancel: false,
        success: function (res) {
          return
        }
      });
    }
    var url = app.util.url("shangchuan/image");
    console.log(url)
    for (var j in upload_picture_listyet) {
      if (upload_picture_listyet[j]['upload_percent'] == 0) {　　　　　　 //调用函数
        //上传返回值
        var that = this;
        const upload_task = wx.uploadFile({
          // 模拟https
          url: url, //需要用HTTPS，同时在微信公众平台后台添加服务器地址  
          filePath: upload_picture_listyet[j]['path'], //上传的文件本地地址    
          name: 'file',
          formData: {
            'num': j
          },
          //附近数据，这里为路径     
          success: function (res) {
            console.log(res)
            wx.showToast({
              title: '图片上传成功',
              icon: "none"
            })
            var jsonStr = res.data;
            jsonStr = jsonStr.replace(" ", "");
            if (typeof jsonStr != 'object') {
              jsonStr = jsonStr.replace(/\ufeff/g, ""); //重点
              var jj = JSON.parse(jsonStr);
              res.data = jj;
            }
            var imgsrcyet = that.data.imgsrcyet
            imgsrcyet = imgsrcyet + res.data.srcImg + ",";
            that.setData({
              imgsrcyet: imgsrcyet,
              uploadyet:true
            })
            console.log("洗车后图片路径")
            console.log(that.data.imgsrcyet)
          },
          fail: function (err) {
            console.log("上传服务器失败")
          } //请求失败
        })
      }
    }
  },



  // 图片预览
  previewImage: function (e) {
    console.log(e)
    var current = e.target.dataset.src
    wx.previewImage({
      current: current,
      urls: [current]
    })
    console.log("这是1" + current);
  },
  // 获取车牌号
  getcarcode:function (e){
    var that = this
    var carcode = e.detail.value
    that.setData({
      carcode: carcode
    })
    console.log(that.data.carcode)
  },
  // 打开导航
  chooseLocation: function (e) {
    var that = this
    var latitude = Number(that.data.orderone.orderOther1);
    var longitude = Number(that.data.orderone.orderOther2);
    wx.openLocation({
      longitude: longitude,
      latitude: latitude
    })
  },

  //  提交
  present:function (e){
    var that = this;
    var carcode = that.data.carcode //车牌号
    if(carcode ==""){
      wx.showModal({
        content: "请输入车牌号",
        showCancel: false,
        success: function (res) {
       return;
        }
      });
      return;
    }
    var orderId = that.data.orderone.orderId
    var orderBeforepic = that.data.imgsrcago
    orderBeforepic = (orderBeforepic == "") ? null : orderBeforepic;
    var orderEndpic = that.data.imgsrcyet
    orderEndpic = (orderEndpic == "") ? null : orderEndpic;
    var orderStatic = that.data.orderStatic
     if(orderEndpic!=null){
       orderStatic = "WC"
     }else{
       orderStatic = null
     }
    app.util.request({
      url: 'washOrder/update',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        orderId: orderId,
        orderNumber:carcode,
        orderBeforepic: orderBeforepic,
        orderStatic: orderStatic,
        orderEndpic: orderEndpic,
      },
      success: function (res) {
        console.log(res)
        if (res.data.code == 200) {
          wx.showModal({
            content: res.data.msg,
            showCancel: false,
            success: function (res) {
              wx.switchTab({
                url: '../my/order/order',
              })
            }
          });
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
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    var orderid = options.orderid
    app.util.request({
      url: 'washOrder/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        orderId: orderid,
      },
      success: function (res) {
        console.log(res)
        var endtime = res.data.data[0].orderEndtime
        endtime =   endtime.substring(11, 19);
 
        var Ttime = res.data.data[0].orderStarttime + "-" + endtime
        that.setData({
          orderone: res.data.data[0],
          Ttime: Ttime,
          carcode: res.data.data[0].orderNumber
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
  onReady: function () {
    
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})