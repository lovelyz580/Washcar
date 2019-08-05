// pages/orderdetail/orderdetail.js
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
    imgsrcago: "",
    imgsrcyet: "",
    carcode: "",
    uploadago: false,
    uploadyet: false,
    url: app.util.url(""),
  },

  // 更新订单
  updateorder: function (e) {
    var orderid = e.currentTarget.dataset.id
    console.log(orderid)
    wx.navigateTo({
      url: '../form/form?orderid=' + orderid
    })
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
  getcarcode: function (e) {
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
        endtime = endtime.substring(11, 19);
        var Ttime = res.data.data[0].orderStarttime + "-" + endtime
        that.setData({
          orderone: res.data.data[0],
          Ttime: Ttime
        })
        var imgsrcago = res.data.data[0].orderBeforepic
        if (imgsrcago != null) {
          var m = imgsrcago.split(",");
          that.setData({
            upload_picture_listago: m
          })
        }

        var imgsrcyet = res.data.data[0].orderEndpic
        if (imgsrcyet != null) {
          var t = imgsrcyet.split(",");
          that.setData({
            upload_picture_listyet: t
          })
        }
        var orderstatic = that.data.orderone.orderStatic
        var orderid = that.data.orderone.orderId
        if(orderstatic=="YWC"){
          // 查询评价内容
          that.selectevalu(orderid)
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
  // 查询评价
  selectevalu:function(orderid){
    var that  = this
    app.util.request({
      url: 'washEvaluate/selectByConditionData',
      header: {
        "Content-Type": "application/json;charset=UTF-8"
      },
      method: "POST",
      data: {
        pagenumber: -1,
        evaluateOrderid: orderid,
      },
      success: function (res) {
        console.log(res)
        that.setData({
          evaluate:res.data.data[0],
          flag: res.data.data[0].evaluateIsgood,
          info: res.data.data[0].evaluateContent,
        })
      }})
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