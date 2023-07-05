<template>
<!--  action="http://124.71.166.37:9091/file/upload"-->
<!--  action="http://124.71.166.37:9091/file/avartar/upload"-->

  <div align="center" style="min-height: 6000px">
    <el-card style="width: 500px;">
      <!--    根据实际表格情况，进行增删-->
      <el-form label-width="80px" size="small">
        <el-upload
            style="text-align: center"
            class="avatar-uploader"
            action="http://124.71.166.37:9091/file/avartar/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :data="this.form"
        >
          <!--        暂时没有头像，因为新增头像需要添加数据库里面-->
          <img v-if="form.avatarurl" :src="form.avatarurl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-form-item label="账号">
          <el-input v-model="form.username" autocomplete="off" disabled/>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="form.phone" autocomplete="off" />
        </el-form-item>
        <el-row>
          <el-button type="primary" size="medium"  style="width: 20%" @click="openDialog">修改密码</el-button>
          <el-button type="success" size="medium" style="width: 20%" @click="save">确认</el-button>
        </el-row>
      </el-form>
    </el-card>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="30%" :close-on-click-modal="false">
      <el-form label-width="80px" :model="ruleForm" status-icon :rules="rules"  ref="checkForm">
        <el-form-item label="新密码"  prop="pass">
          <el-input type="password" v-model="ruleForm.pass" ></el-input>
        </el-form-item>
        <el-form-item label="确认密码"  prop="checkPass">
          <el-input type="password" v-model="ruleForm.checkPass" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="savePassword">保 存</el-button>
      </div>
    </el-dialog>


  </div>
</template>


<script>
import { MD5 } from 'crypto-js';
export default {
  name: "Person",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.checkForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      },
      dialogFormVisible:false,
      form: {},
      user: localStorage.getItem("loguserinfo") ? JSON.parse(localStorage.getItem("loguserinfo")) : {}
    }
  },

  // 这里暂时写的是学生，后面需要更换成别的端口
  created() {
    this.getUser().then(res =>{
      console.log(res)
      this.form = res
    })
  },
  methods:{
    openDialog(){
      this.dialogFormVisible=true
    },
    encryptPassword(password){
      const  enctypotedPassword = MD5(password).toString();
      return enctypotedPassword;
    },
    savePassword(){
      this.$refs.checkForm.validate((valid) => {
        if (valid) {
          this.form.password=this.ruleForm.pass
          //md5加密
          this.form.password = this.encryptPassword(this.form.password);
          // this.$message.success("加密后的密码"+this.form.password);
          // this.$message.success("当前用户id"+this.form.id);
          this.request.post("/users/changepassword",this.form).then(res=>{
            if(res){
              this.$message.success("保存成功")
              this.dialogFormVisible=false
            }else {
              this.$message.error("保存失败")
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    async getUser(){
      return  (await this.request.get("/users/" + this.user.username)).data
    },
    save(){
      //发送数据到后端
      //this.$message.success("保存信息："+this.form.avatarUrl)
      this.request.post("/users",this.form).then(res => {
        if(res){
          this.$message.success("保存成功")
          //触发父级更新user的方法
          this.$emit("refreshUser")
          //保存之后，触发manage的父级，通过父级中的功能来实现更新以及右上角头像的更新
          //更新浏览器存储信息
          this.getUser().then(res =>{
            // res.token  = JSON.parse(localStorage.getItem("loguserinfo")).token
            //localStorage.removeItem("loguserinfo")
            localStorage.setItem("loguserinfo",JSON.stringify(res))
          })
          this.$router.push("/home") //跳转到学生登录界面
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAvatarSuccess(res){
      this.form.avatarurl = res
      this.$message.success("保存成功")
    }
  }
}
</script>
<style>
.avatar-uploader{
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload{
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 158px;
  height: 158px;
  line-height: 158px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}


</style>