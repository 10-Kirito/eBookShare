"use strict";(self["webpackChunkfrontdesk"]=self["webpackChunkfrontdesk"]||[]).push([[784],{784:function(t,e,a){a.r(e),a.d(e,{default:function(){return d}});a(7658);var i=function(){var t=this,e=t._self._c;return e("div",[e("div",{staticStyle:{margin:"10px 0"}},[e("el-carousel",{attrs:{height:"450px",interval:1e4}},t._l(t.lun,(function(t){return e("el-carousel-item",{key:t.id},[e("a",{attrs:{href:t.link,target:"_blank"}},[e("img",{staticStyle:{width:"100%",height:"100%"},attrs:{src:t.image,alt:""}})])])})),1)],1),e("div",{staticStyle:{margin:"10px 0"}},[e("div",{staticStyle:{margin:"20px 0","font-size":"20px",color:"orangered","border-bottom":"1px solid orangered","padding-bottom":"10px"}},[t._v("商品列表")]),e("el-row",{attrs:{gutter:10}},t._l(t.products,(function(a){return e("el-col",{key:a.id,staticStyle:{"margin-bottom":"10px"},attrs:{span:6}},[e("div",{staticStyle:{"background-color":"white",padding:"10px",cursor:"pointer"},on:{click:function(e){return t.$router.push("/shop/detail?id="+a.id)}}},[e("img",{staticStyle:{width:"100%",height:"200px",overflow:"hidden","border-radius":"10px"},attrs:{src:a.image,alt:""}}),e("div",{staticClass:"text-line1",staticStyle:{color:"#666",padding:"10px"}},[t._v(t._s(a.name))]),e("div",{staticStyle:{margin:"10px 0",color:"red","font-weight":"bold","font-size":"14px"}},[t._v(t._s(a.price)+" 积分")])])])})),1),e("div",{staticStyle:{padding:"10px 0"}},[e("el-pagination",{attrs:{background:"",align:"center","current-page":t.pageNum,"page-sizes":[4,8,12],"page-size":t.pageSize,layout:"total, prev, pager, next",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)])},r=[],n={name:"Home",data(){return{lun:[],products:[],total:0,pageNum:1,pageSize:8}},created(){this.load()},methods:{load(){this.request.get("/lunbo").then((t=>{this.lun=t})),this.request.get("/products/page",{params:{pageNum:this.pageNum,pageSize:this.pageSize,name:this.name}}).then((t=>{this.products=t.records,this.total=t.total}))},handleSizeChange(t){this.pageSize=t,this.load()},handleCurrentChange(t){this.pageNum=t,this.load()}}},s=n,o=a(1001),l=(0,o.Z)(s,i,r,!1,null,"461bf090",null),d=l.exports}}]);
//# sourceMappingURL=784.ce04d055.js.map