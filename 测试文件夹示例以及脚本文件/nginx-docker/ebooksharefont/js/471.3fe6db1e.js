"use strict";(self["webpackChunkfrontdesk"]=self["webpackChunkfrontdesk"]||[]).push([[471],{3471:function(e,t,l){l.r(t),l.d(t,{default:function(){return d}});var o=function(){var e=this,t=e._self._c;return t("div",[t("div",[e._v("这里是图书详情模板组件!")]),t("br"),t("el-upload",{staticClass:"upload-demo",attrs:{action:"http://124.71.166.37:9091/file/upload","on-preview":e.handlePreview,"on-remove":e.handleRemove,"before-remove":e.beforeRemove,multiple:"",limit:100,"on-exceed":e.handleExceed,"file-list":e.fileList}},[t("el-button",{attrs:{size:"small",type:"primary"}},[e._v("Click to upload")]),t("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("pdf files")])],1),t("br"),t("el-button",{staticStyle:{"margin-top":"10px","margin-bottom":"10px"},attrs:{type:"primary"},on:{click:e.previewBook}},[e._v("预览")])],1)},a=[],i={name:"BookDetails",data(){return{fileList:[{name:"food.jpeg",url:"https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"},{name:"food2.jpeg",url:"https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100"}],booksDetail:[]}},methods:{handleRemove(e,t){console.log(e,t)},handlePreview(e){console.log(e)},handleExceed(e,t){this.$message.warning(`The limit is 3, you selected ${e.length} files this time, add up to ${e.length+t.length} totally`)},beforeRemove(e,t){return this.$confirm(`Cancel the transfert of ${e.name} ?`)},previewBook(){let e="http://localhost:9090/file/1de53068902f460b9c7b3bbe99719460.pdf";window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${e}`)}}},s=i,n=l(3736),r=(0,n.Z)(s,o,a,!1,null,"14a8ceae",null),d=r.exports}}]);
//# sourceMappingURL=471.3fe6db1e.js.map