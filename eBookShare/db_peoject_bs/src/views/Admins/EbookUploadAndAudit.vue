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
          <el-upload
              type="file"
              ref="upload"
              :data="carryData"
              class="upload-demo"
              action="http://localhost:9090/auditbooks/upload"
              :limit="1"
              :auto-upload="false"
              :on-change="loadJsonFromFile"
              :on-success="updatebookinfo"
              :on-error="uploadError"
              :before-upload="beforeUpload"
              :before-remove="beforeRemove"
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

    loadJsonFromFile(file, fileList) {
      //判断文件大小
      this.uploadFiles = fileList
      var FileExt = file.name.replace(/.+\./, "")
      const isLt5M = file.size / 1024 / 1024 < 100
      var extension = ['pdf', 'epub'].indexOf(FileExt.toLowerCase()) === -1
      if (extension){
        this.$message({
          type: 'warning',
          message: '只能上传 PDF  EPUB 文件'
        })
        return false
      }
      if (!isLt5M) {
        this.$message({
          type: 'warning',
          message: '附件大小超限，文件不能超过 100M'
        })
        return false
      }

    },
    beforeUpload(file){
      console.log('文件：', file)
      var FileExt = file.name.replace(/.+\./, "")
      const isLtM = file.size / 1024 / 1024 < 100
      var extension = ['pdf', 'epub'].indexOf(FileExt.toLowerCase()) === -1
      if (extension){
        this.$message({
          type: 'warning',
          message: '只能上传 PDF  EPUB 文件'
        })
        return false
      }
      if (!isLtM) {
        this.$message({
          type: 'warning',
          message: '附件大小超限，文件不能超过 100M'
        })
        return false
      }

    },
    beforeRemove(file,fileList){
      //设置不符合条件的时候，预览框自动删除
      var FileExt = file.name.replace(/.+\./, "")
      var isLtM = file.size / 1024 / 1024 < 100
      var extension = ['pdf', 'epub'].indexOf(FileExt.toLowerCase()) === -1
      if (!extension || !isLtM) {
        var i = fileList.indexOf(file)
        fileList.splice(i, 1) // 自动删除不符合要求的文件，不让它出现在预览列表中
        return false // 只有return false 才会真的限制
      } else {
        return this.$confirm(`确定移除 ${file.name}？`)
      }
    },

    updatebookinfo(){
      //随后上传文件信息
      let file = this.uploadFiles[0]
      // this.$message.error("获取文件名："+file.name)
      this.$message.error("获取信息：" + file.name)
      this.request.get("/auditbooks/addbookinfo", {
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
              this.$router.push("/admins/ebookmanage")
            } else {
              this.$message.error("上传失败")
            }
          })
    },
    uploadError(){
      this.$message.error("文件上传失败")
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