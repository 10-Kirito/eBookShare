<template>
  <div>
    <el-card style="width: 500px;margin: 50px auto auto;" >
      <!--    根据实际表格情况，进行增删-->
      <el-form label-width="80px" size="small">
<!--        <el-upload-->
<!--            style="text-align: center"-->
<!--            class="avatar-uploader"-->
<!--            action="http://localhost:9090/admins/upload"-->
<!--            :show-file-list="false"-->
<!--            :on-success="handleAvatarSuccess"-->
<!--        >-->
<!--          &lt;!&ndash;        暂时没有头像，因为新增头像需要添加数据库里面&ndash;&gt;-->
<!--          <img v-if="form.avatarurl" :src="form.avatarurl" class="avatar" />-->
<!--          <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--        </el-upload>-->



        <el-form-item label="书名">
          <el-input v-model="form.bookname" autocomplete="off" placeholder="请输入书籍名称"/>
        </el-form-item>
        <el-form-item label="作者">
          <el-input v-model="form.author" autocomplete="off" placeholder="请输入作者"/>
        </el-form-item>
        <el-form-item label="出版社">
          <el-input v-model="form.publisher" autocomplete="off" placeholder="请输入出版社"/>
        </el-form-item>
        <el-form-item label="isbn">
          <el-input v-model="form.isbn" autocomplete="off" placeholder="请输入书籍isbn"/>
        </el-form-item>
        <el-form-item label="简介" >
          <el-input type="textarea" v-model="form.description" rows="6" autocomplete="off" placeholder="请输入简介"/>
        </el-form-item>
        <el-form-item label="分类">
          <el-input v-model="form.category" autocomplete="off" placeholder="请输入分类"/>
        </el-form-item>
        <el-form-item>
<!--注意！！这里的分类以后需要自己挑选-->
          <el-upload
              type="file"
              ref="upload"
              :data="carryData"
              class="upload-demo"
              action="http://localhost:9090/books/upload"
              :limit="1"
              :auto-upload="false"
              :on-change="loadJsonFromFile"
              :on-success="updatebookinfo"
          >
            <template #trigger>
              <el-button type="primary" class="ml-5" style="margin: 20px 60px auto" >选择</el-button>
            </template>
            <el-button class="ml-5" type="success" style="margin-bottom: 20px" @click="submitUpload">
              确定
            </el-button>
            <template #tip>
              <!--            <div class="el-upload__tip text-red">-->
              <!--              limit 1 file, new file will cover the old file-->
              <!--            </div>-->
            </template>
          </el-upload>
        </el-form-item>

      </el-form>

    </el-card>
  </div>
</template>

<script>


export default {
  name: "Person",
  data() {
    return {
      form: {},
      file:'',
      uploadFiles: [],
      user: localStorage.getItem("loguserinfo") ? JSON.parse(localStorage.getItem("loguserinfo")) : {}
    }
  },

  // 这里暂时写的是学生，后面需要更换成别的端口
  created() {
    this.getUser().then(res =>{
      this.form = res
    })
  },
  methods:{
    async getUser(){
      return  (await this.request.get("/student/studentid/" + this.user.studentid)).data
    },
    // save(){
    //   //发送数据到后端
    //   //this.$message.success("保存信息："+this.form.avatarUrl)
    //   this.request.post("/student",this.form).then(res => {
    //     if(res){
    //       this.$message.success("保存成功")
    //       //触发父级更新user的方法
    //       this.$emit("refreshUser")
    //       //保存之后，触发manage的父级，通过父级中的功能来实现更新以及右上角头像的更新
    //
    //
    //       //更新浏览器存储信息
    //       this.getUser().then(res =>{
    //         res.token  = JSON.parse(localStorage.getItem("loguserinfo")).token
    //         //localStorage.removeItem("loguserinfo")
    //         localStorage.setItem("loguserinfo",JSON.stringify(user))
    //       })
    //     }else {
    //       this.$message.error("保存失败")
    //     }
    //   })
    // },
    loadJsonFromFile(file, fileList) {
      this.uploadFiles = fileList
    },
    updatebookinfo(){
      //随后上传文件信息
      let file = this.uploadFiles[0]
      // this.$message.error("获取文件名："+file.name)
      this.$message.error("获取信息：" + file.name)
      this.request.get("/books/addbookinfo", {
        params: {
          filename: file.name,
          bookname: this.form.bookname,
          author: this.form.author,
          publisher: this.form.publisher,
          isbn: this.form.isbn,
          description: this.form.description,
          category: this.form.category,
        }
      })
          .then(res => {
            if (res.code === '200') {
              this.$message.success("上传成功")
            } else {
              this.$message.error("上传失败")
            }
          })
    },
    submitUpload() {
      //先上传文件
      // const file = this.$refs.upload.files;
      // this.$message.error("获取文件名："+this.$refs.upload.files.name)
      this.$refs.upload.submit()


    },

    handleAvatarSuccess(res){
      this.form.avatarurl = res
      //this.$message.success("路径"+this.form.avatarUrl)
      //this.$message.success("路径"+res)
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