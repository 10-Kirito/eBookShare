"use strict";(self["webpackChunkdb_peoject_bs"]=self["webpackChunkdb_peoject_bs"]||[]).push([[900],{2900:function(e,t,s){s.r(t),s.d(t,{default:function(){return m}});var r=function(){var e=this,t=e._self._c;return t("div",{attrs:{align:"center"}},[t("el-card",{staticStyle:{width:"500px","margin-top":"150px"}},[t("el-form",{attrs:{"label-width":"80px",size:"small"}},[t("el-form-item",{staticStyle:{"margin-top":"35px"},attrs:{label:"账号"}},[t("el-input",{attrs:{autocomplete:"off",disabled:""},model:{value:e.form.username,callback:function(t){e.$set(e.form,"username",t)},expression:"form.username"}})],1),t("el-form-item",{attrs:{label:"密码"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.password,callback:function(t){e.$set(e.form,"password",t)},expression:"form.password"}})],1),t("el-form-item",{attrs:{label:"邮箱"}},[t("el-input",{attrs:{autocomplete:"off"},model:{value:e.form.email,callback:function(t){e.$set(e.form,"email",t)},expression:"form.email"}})],1),t("el-form-item",[t("el-button",{staticStyle:{width:"50%","margin-left":"-80px"},attrs:{type:"primary"},on:{click:e.save}},[e._v("确认")])],1)],1)],1)],1)},a=[],l={name:"Person",data(){return{form:{},user:localStorage.getItem("loguserinfo")?JSON.parse(localStorage.getItem("loguserinfo")):{}}},created(){this.getUser().then((e=>{console.log(e),this.form=e}))},methods:{async getUser(){return(await this.request.get("/admins/getadmins/"+this.user.id)).data},save(){this.request.post("/admins",this.form).then((e=>{e?(this.$message.success("保存成功"),this.$emit("refreshUser"),this.getUser().then((e=>{localStorage.setItem("loguserinfo",JSON.stringify(e))}))):this.$message.error("保存失败")}))},handleAvatarSuccess(e){this.form.avatarurl=e,this.$message.success("保存成功")}}},o=l,i=s(1001),n=(0,i.Z)(o,r,a,!1,null,null,null),m=n.exports}}]);
//# sourceMappingURL=900.5b116703.js.map