"use strict";(self["webpackChunkfrontdesk"]=self["webpackChunkfrontdesk"]||[]).push([[990],{1990:function(t,e,r){r.r(e),r.d(e,{default:function(){return l}});var s=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticStyle:{display:"flex",margin:"10px 0"}},[e("div",{staticStyle:{width:"40%"}},[e("el-image",{staticStyle:{width:"100%",height:"320px"},attrs:{src:t.product.image?t.product.image:""}})],1),e("div",{staticStyle:{"margin-left":"10px",flex:"1"}},[e("el-card",[e("el-form",{attrs:{"label-width":"80px"}},[e("el-form-item",{attrs:{label:"商品名称"}},[t._v(t._s(t.product.name))]),e("el-form-item",{attrs:{label:"商品类别"}},[t._v(t._s(t.category))]),e("el-form-item",{attrs:{label:"积分价格"}},[e("span",{staticStyle:{color:"red"}},[t._v(t._s(t.product.price)+" /"+t._s(t.product.unit))])]),e("el-form-item",{attrs:{label:"商品库存"}},[e("span",[t._v(t._s(t.product.nums)+" "+t._s(t.product.unit))])]),e("div",[e("el-input-number",{staticStyle:{width:"150px"},attrs:{value:1,size:"medium"},model:{value:t.buyNum,callback:function(e){t.buyNum=e},expression:"buyNum"}}),e("el-button",{staticStyle:{background:"red","font-size":"16px",color:"white",padding:"10px","margin-left":"5px"},on:{click:t.addCart}},[t._v("加入购物车")])],1),e("div",{staticStyle:{"margin-top":"20px","font-size":"12px",color:"#888"}},[t._v(" 温馨提示     ·支持7天无理由退货 ")])],1)],1)],1)]),e("div",{staticStyle:{margin:"20px 0"}},[e("el-card",[e("div",{staticStyle:{margin:"10px 0","font-size":"18px","border-bottom":"1px solid #ccc","padding-bottom":"10px"}},[t._v("商品评价")]),t._l(t.comments,(function(r){return e("div",{key:r.id,staticStyle:{margin:"10px 0"}},[e("div",[e("el-image",{staticStyle:{width:"30px",height:"30px","border-radius":"50%"},attrs:{src:r.avatar}}),e("span",{staticStyle:{"margin-left":"5px"}},[t._v(t._s(r.member))]),e("span",{staticStyle:{"margin-left":"20px",color:"#666","font-size":"14px"}},[t._v(t._s(r.comment))])],1)])}))],2)],1)])},i=[],a={name:"Detail",data(){let t=this.$route.query.id;return{product:{},productId:t,category:"",buyNum:1,user:localStorage.getItem("loguserinfo")?JSON.parse(localStorage.getItem("loguserinfo")):{},comments:[]}},async created(){await this.request.get("/products/"+this.productId).then((t=>{this.product=t})),this.request.get("/productcategories/"+this.product.productCategoryId).then((t=>{this.category=t.name})),this.request.get("/orderdetails/comment/"+this.productId).then((t=>{this.comments=t.data}))},methods:{addCart(){this.request.post("/cart",{productId:this.productId,num:this.buyNum,memberId:this.user.id}).then((t=>{"200"===t.code?this.$message.success("加入购物车成功"):this.$message.error(t.msg)}))}}},o=a,c=r(1001),d=(0,c.Z)(o,s,i,!1,null,"61b53eeb",null),l=d.exports}}]);
//# sourceMappingURL=990.c0027b2e.js.map