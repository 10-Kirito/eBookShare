"use strict";(self["webpackChunkfrontdesk"]=self["webpackChunkfrontdesk"]||[]).push([[66],{1729:function(t,e,s){s.r(e),s.d(e,{default:function(){return c}});var o=function(){var t=this,e=t._self._c;return e("div",{staticStyle:{width:"1000px",margin:"16px auto"}},[t._l(t.commentsList,(function(s,o){return e("div",{key:o,staticClass:"author-title reply-father"},[e("el-avatar",{staticClass:"header-img",attrs:{size:40,src:s.avatarurl}}),e("div",{staticClass:"author-info"},[e("span",{staticClass:"author-name"},[t._v(t._s(s.username))]),e("span",{staticClass:"author-time"},[t._v(t._s(s.time))])]),e("div",{staticClass:"talk-box"},[e("p",[e("span",{staticClass:"reply"},[t._v(t._s(s.text))])])])],1)})),e("el-pagination",{attrs:{"current-page":t.pageNum,"page-sizes":[2,5,10,20],"page-size":t.pageSize,layout:"total, prev, pager, next, jumper",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],2)},i=[],a={name:"comments",props:{bookDetail:{}},data(){return{commentsList:[],total:0,pageNum:1,pageSize:10,imagesurl:s(2421)}},created(){this.load()},methods:{load(){this.request.get("/commits/findcommits",{params:{pageNum:this.pageNum,pageSize:this.pageSize,bookid:this.bookDetail.bookid}}).then((t=>{"200"===t.code&&(this.commentsList=t.data.records,this.total=t.data.total,this.$emit("custom-event",this.total))}))},handleSizeChange(t){this.pageSize=t,this.load()},handleCurrentChange(t){this.pageNum=t,this.load()}}},l=a,r=s(1001),n=(0,r.Z)(l,o,i,!1,null,"a7a630ce",null),c=n.exports},9066:function(t,e,s){s.r(e),s.d(e,{default:function(){return u}});var o=function(){var t=this,e=t._self._c;return e("div",[t.bookDetails.bookname?e("div",{staticClass:"parent-container"},[e("el-container",{staticClass:"el-container"},[e("el-main",[e("el-breadcrumb",{staticStyle:{"font-size":"20px","margin-bottom":"20px"},attrs:{"separator-class":"el-icon-arrow-right"}},[e("el-breadcrumb-item",{staticStyle:{color:"red"},attrs:{to:{path:"/"}}},[t._v("主页")]),e("el-breadcrumb-item",[t._v(t._s(t.bookDetails.bookname))])],1),e("el-row",{staticClass:"book-card",attrs:{book_id:6138195}},[e("el-col",{staticClass:"book-cover",attrs:{xs:24,sm:6}},[e("div",[e("el-image",{staticStyle:{height:"26pc"},attrs:{src:t.bookDetails.coverimage,fit:"cover",lazy:""}})],1),e("el-button",{staticStyle:{"margin-left":"0px","margin-top":"5px",width:"340px"},attrs:{type:"success",icon:"el-icon-view",plain:""},on:{click:t.previewBook}},[t._v("预览")]),e("el-button",{staticStyle:{"margin-left":"0px","margin-top":"5px",width:"340px"},attrs:{type:"primary",icon:"el-icon-download",plain:""},on:{click:t.downloads}},[t._v("下载")])],1),e("el-col",{staticClass:"book-info",attrs:{xs:24,sm:18}},[e("el-row",{staticClass:"align-left"},[e("h4",{staticClass:"book-title",staticStyle:{margin:"0px",padding:"0px"}},[t._v(t._s(t.bookDetails.bookname))])]),e("el-row",{staticClass:"align-left"},[e("i",{staticClass:"book-author"},[e("br"),e("span",{staticClass:"color1",staticStyle:{cursor:"pointer"},attrs:{title:"找到该作者的所有书籍"},on:{click:function(e){return t.redirectToAuthorBooks(t.bookDetails.author)}}},[t._v("作者:"+t._s(t.bookDetails.author))])])]),e("el-row",{staticClass:"align-left"},[e("div",{staticStyle:{"font-size":"20px",display:"inline-block"}},[e("el-popover",{attrs:{placement:"bottom",width:"230",trigger:"click"}},[e("div",{staticStyle:{"font-size":"12px","text-align":"center"}},[t._v("-----------你有多喜欢这本书？-----------")]),e("div",{staticClass:"block",staticStyle:{"text-align":"center"}},[e("el-rate",{on:{change:t.rateChange},model:{value:t.bookValue,callback:function(e){t.bookValue=e},expression:"bookValue"}})],1),e("i",{staticClass:"el-icon-medal",staticStyle:{cursor:"pointer"},attrs:{slot:"reference"},slot:"reference"})]),e("el-tooltip",{attrs:{effect:"dark",content:"图书评级/图书总评",placement:"bottom"}},[e("i",[e("span",{staticClass:"book-rating-interest-score"},[t._v(t._s(t.bookValue))]),t._v(" / "),e("span",{staticClass:"book-rating-quality-score"},[t._v(t._s(t.bookAvgValue[0]))])])])],1),e("div",{staticClass:"ml-5",staticStyle:{display:"inline-block"}},[e("el-tooltip",{attrs:{content:"评论"}},[e("el-button",{staticClass:"el-icon-chat-dot-square",staticStyle:{cursor:"pointer","font-size":"15px","margin-right":"5px"},attrs:{circle:""},on:{click:t.jumpToInput}})],1),e("span",{staticStyle:{"font-size":"20px","margin-right":"15px"}},[t._v(t._s(t.commentcount)+" comments")])],1),e("el-tooltip",{attrs:{content:t.bookDetails.isCollected?"取消收藏":"收藏"}},[e("el-button",{staticClass:"book-add-to-list",class:t.bookDetails.collectBtnClass,attrs:{circle:""},on:{click:t.collectBtn}})],1),e("el-tooltip",{attrs:{content:t.bookDetails.islike?"取消点赞":"点赞"}},[e("el-button",{staticClass:"book-thank-contributors",attrs:{icon:"el-icon-thumb",circle:""},on:{click:t.likeBtn}})],1)],1),e("el-row",{attrs:{justify:"space-between"}},[e("p",{staticClass:"grid-content",staticStyle:{"text-align":"justify"}},[t._v(t._s(t.bookDetails.description))])]),e("el-row",{staticClass:"row-bg",attrs:{gutter:20}},[e("el-col",{attrs:{span:2}},[e("div",{staticClass:"grid-content col-content"},[t._v("种类:")])]),e("el-col",{attrs:{span:6}},[e("div",{staticClass:"grid-content col-content"},[t._v(t._s(t.bookDetails.category))])]),e("el-col",{attrs:{span:2}},[e("div",{staticClass:"grid-content col-content"},[t._v("出版社：")])]),e("el-col",{attrs:{span:6}},[e("div",{staticClass:"grid-content col-content"},[t._v(t._s(t.bookDetails.publisher))])])],1),e("el-row",{staticClass:"row-bg",attrs:{gutter:20}},[e("el-col",{attrs:{span:2}},[e("div",{staticClass:"grid-content col-content"},[t._v("文件类型:")])]),e("el-col",{attrs:{span:6}},[e("div",{staticClass:"grid-content col-content"},[t._v(t._s(t.bookDetails.type))])]),e("el-col",{attrs:{span:2}},[e("div",{staticClass:"grid-content col-content"},[t._v("ISBN:")])]),e("el-col",{attrs:{span:6}},[e("div",{staticClass:"grid-content col-content"},[t._v(t._s(t.bookDetails.isbn))])])],1)],1)],1)],1),e("el-footer",[e("div",{staticClass:"grid-content bg-purple-dark",staticStyle:{width:"900px",margin:"10px auto"}},[e("p",{staticStyle:{color:"#f9fafc","font-family":"'Arial Narrow'","font-size":"25px","margin-left":"10px"}},[t._v("评论：")])]),e("div",{staticStyle:{width:"700px",margin:"0 auto"}},[e("el-input",{ref:"inputRef",staticStyle:{"font-size":"20px"},attrs:{type:"textarea",rows:2,placeholder:"请输入内容"},model:{value:t.textarea,callback:function(e){t.textarea=e},expression:"textarea"}}),e("div",{staticStyle:{"text-align":"right",margin:"10px 0"}},[e("el-button",{attrs:{type:"success"},on:{click:t.submitComment}},[t._v("提交")])],1)],1),e("div",{staticStyle:{margin:"10px auto"}},[e("comments",{key:t.commentKey,attrs:{bookDetail:t.bookDetails},on:{"custom-event":t.handleCustomEvent}})],1)])],1)],1):t._e()])},i=[],a=(s(7658),s(1729)),l={name:"BookDetails",components:{comments:a["default"]},created(){const t=this.$route.query.params;if(t)try{this.bookDetails=JSON.parse(t),console.log("数据是：",this.bookDetails)}catch(e){console.error("解析参数出错：",e)}else console.warn("未找到参数");this.getBookAvgScore(),this.getBookScore()},data(){return{bookDetails:{},collectBtnClass:"el-icon-star-off",bookValue:0,bookAvgValue:[],textarea:null,user:localStorage.getItem("loguserinfo")?JSON.parse(localStorage.getItem("loguserinfo")):{id:0},commentKey:0,commentcount:""}},methods:{jumpToInput(){this.$refs.inputRef.$el.scrollIntoView({behavior:"smooth"})},handleCustomEvent(t){this.commentcount=t},collectBtn(){0!=this.user.id?(this.bookDetails.isCollected=!this.bookDetails.isCollected,this.bookDetails.isCollected?(this.$message.success("收藏成功"),this.bookDetails.collectBtnClass="el-icon-star-on",this.request.get("/relationship/favourbook",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{console.log(t)})),console.log("收藏")):(this.$message.success("取消成功"),this.bookDetails.collectBtnClass="el-icon-star-off",this.request.get("/relationship/favourbook",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{console.log(t)})),console.log("取消收藏"))):this.$message.error("请先登录")},likeBtn(){0!==this.user.id?this.bookDetails.islike?(this.$message.success("取消点赞成功"),this.bookDetails.islike=!this.bookDetails.islike,this.request.get("/relationship/likebook",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{console.log(t)}))):(this.$message.success("点赞成功"),this.bookDetails.islike=!this.bookDetails.islike,this.request.get("/relationship/likebook",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{console.log(t)}))):this.$message.error("请先登录")},redirectToAuthorBooks(t){this.$router.push({path:"/searchResult",query:{params:JSON.stringify(t)}})},previewBook(){let t=this.bookDetails.url;window.open("/lib/pdfjs-3.5.141-dist/web/viewer.html?file="+(t+"/preview"))},submitComment(){0===this.user.id?this.$message.error("评论失败，请先登录！"):this.request.get("/commits/mankecommits",{params:{userid:this.user.id,bookid:this.bookDetails.bookid,text:this.textarea}}).then((t=>{"200"===t.code?(this.$message.success("评论成功"),this.commentKey++):this.$message.error("评论失败，请先登录！")}))},downloads(){0!==this.user.id?this.request.get("/users/pointbuybook",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{"200"===t.code?(window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${this.bookDetails.url}`),this.$message.success("下载成功")):"600"===t.code?this.$message.error("积分不足，下载失败"):"10"===t.code&&(window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${this.bookDetails.url}`),this.$message.success("下载成功"))})):this.$message.error("请先登录")},getBookAvgScore(){this.request.get("/FrontBooks/GetAvgScore",{params:{bookid:this.bookDetails.bookid}}).then((t=>{null==t.data?this.bookAvgValue=[0]:this.bookAvgValue=t.data,console.log(this.bookAvgValue)}))},getBookScore(){this.request.get("/FrontBooks/getscore",{params:{bookid:this.bookDetails.bookid,userid:this.user.id}}).then((t=>{"200"===t.code?this.bookValue=t.data.score:this.bookValue=0}))},rateChange(){0===this.user.id?this.$message.error("评分失败，请先登录！"):this.request.get("/FrontBooks/score",{params:{userid:this.user.id,bookid:this.bookDetails.bookid,score:this.bookValue}}).then((t=>{this.bookAvgValue=t.data,console.log(this.bookAvgValue)}))}}},r=l,n=s(1001),c=(0,n.Z)(r,o,i,!1,null,"2640a98b",null),u=c.exports},2421:function(t,e,s){t.exports=s.p+"img/cutcamera.4097945f.png"}}]);
//# sourceMappingURL=66.6f929b1d.js.map