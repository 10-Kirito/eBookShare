"use strict";(self["webpackChunkfrontdesk"]=self["webpackChunkfrontdesk"]||[]).push([[732],{6732:function(t,e,s){s.r(e),s.d(e,{default:function(){return p}});var a=function(){var t=this,e=t._self._c;return e("div",[e("el-main",[e("h1",[t._v(t._s(t.author)+"的书籍:")]),e("div",{staticStyle:{margin:"0 100px"}},[e("div",[e("div",[e("div",{staticClass:"grid-content bg-purple-light"},[t._l(t.bookDetails,(function(s,a){return e("el-card",{key:a,staticClass:"box-card"},[e("el-row",{attrs:{gutter:20}},[e("el-col",{staticClass:"book-cover",attrs:{span:6}},[e("el-image",{staticStyle:{cursor:"pointer",height:"160px","margin-left":"80px"},attrs:{src:s.img,fit:"contain",lazy:""},on:{click:function(e){return t.pushDetail(a)}}})],1),e("el-col",{attrs:{span:15}},[e("el-row",{staticClass:"attriclass"},[e("el-col",{attrs:{span:23}},[e("h3",{staticStyle:{cursor:"pointer","text-decoration":"underline"},on:{click:function(e){return t.pushDetail(a)}}},[t._v(t._s(s.title))])]),e("el-col",{attrs:{span:1}},[e("el-tooltip",{staticClass:"item",attrs:{effect:"dark",content:s.isCollected?"取消收藏":"收藏"}},[e("i",{class:s.collectBtnClass,staticStyle:{cursor:"pointer","font-size":"25px","margin-left":"18px","margin-top":"20px"},on:{click:function(e){return t.collectBtn(a)}}})])],1)],1),e("el-row",{staticClass:"attriclass"},[e("div",{staticClass:"grid-content book-publisher",attrs:{title:"出版社"}},[t._v(t._s(s.publisher))])]),e("el-row",{staticStyle:{"margin-top":"60px","margin-right":"-70px"},attrs:{gutter:24}},[e("el-col",{attrs:{span:2}},[e("div",{staticStyle:{color:"#8C8C8C"}},[t._v("年:")])]),e("el-col",{attrs:{span:6}},[e("div",{staticStyle:{"margin-top":"2px"}},[t._v(t._s(s.publishedDate))])]),e("el-col",{attrs:{span:4}},[e("div",{staticStyle:{color:"#8C8C8C"}},[t._v("语言：")])]),e("el-col",{attrs:{span:4}},[e("div",{staticStyle:{"margin-top":"2px"}},[t._v(t._s(s.Language))])]),e("el-col",{attrs:{span:4}},[e("div",{staticStyle:{color:"#8C8C8C"}},[t._v("文件：")])]),e("el-col",{attrs:{span:4}},[e("div",{staticStyle:{"margin-top":"2px"}},[t._v(t._s(s.file))])])],1)],1)],1)],1)})),e("el-pagination",{attrs:{"current-page":t.pageNum,"page-sizes":[2,5,10,20],"page-size":t.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:t.total},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],2)])])])])],1)},i=[],l=(s(7658),s(1120)),o={name:"AuthorBooks",data(){return{author:JSON.parse(this.$route.query.params),bookDetails:[{img:"https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",title:"Book Title",author:"Author Name",publisher:"Publisher Name",publishedDate:"2022-01-01",description:"《麦田里的守望者》的主人公,16岁的中学生霍尔顿·考尔菲德是当代美国文学中最早出现的反英雄形象之一。霍尔顿出身在纽约一个富裕的中产阶级的家庭。学校里的老师和自己的家长强迫他好好读书,为的是“出人头地,而他看不惯周围的一切,根本没心思用功读书,因而老是挨罚。他的内心又十分苦闷、彷徨,这种精神上无法调和的极度矛盾最终令他彻底崩溃,躺倒在精神病院里。",ISBN:"978-3-16-148410-0",Format:"Hardcover",Pages:"400",Language:"English",category:"category",file:"PDF",isCollected:!1,collectBtnClass:"el-icon-star-off"},{img:"https://bookcover.yuewen.com/qdbimg/349573/1019103033/180",title:"Book Title 3",author:"Author Name ",publisher:"Publisher Name 3",publishedDate:"2022-01-01",description:"Description of Book 23",ISBN:"ISBN 3",Format:"Hardcover",Pages:"300",Language:"English",category:"category 3",file:"PDF",isCollected:!1,collectBtnClass:"el-icon-star-off"}],total:0,pageNum:1,pageSize:10}},created(){},methods:{load(){this.request.get("/",{params:{pageNum:this.pageNum,pageSize:this.pageSize,auther:this.author}}).then((t=>{this.bookDetails=t.records,this.total=t.total,this.bookDetails.forEach((t=>{t.isCollected=!1,t.collectBtnClass="el-icon-star-off"}))}))},handleSizeChange(t){this.pageSize=t,this.load()},handleCurrentChange(t){this.pageNum=t,this.load()},pushDetail(t){l.Z.push({path:"/bookdetails",query:{params:JSON.stringify(this.bookDetails[t])}})},collectBtn(t){this.bookDetails[t].isCollected=!this.bookDetails[t].isCollected,this.bookDetails[t].isCollected?(this.$message.success("收藏成功"),this.bookDetails[t].collectBtnClass="el-icon-star-on"):(this.$message.success("取消成功"),this.bookDetails[t].collectBtnClass="el-icon-star-off")}}},r=o,c=s(1001),n=(0,c.Z)(r,a,i,!1,null,"40716b35",null),p=n.exports}}]);
//# sourceMappingURL=732.fbd29687.js.map