<template>
  <div>
    <el-card style="width: 500px;margin: 50px auto auto;" >
      <!--    根据实际表格情况，进行增删-->
      <el-form label-width="80px" size="small">
        <el-form-item label="书名">
          <el-input type="textarea" rows="1" v-model="form.bookname" autocomplete="off" placeholder="请输入书籍名称"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input type="textarea" rows="1" v-model="form.author" autocomplete="off" placeholder="请输入作者"/>
        </el-form-item>
        <el-form-item label="出版社">
          <el-input type="textarea" rows="1" v-model="form.publisher" autocomplete="off" placeholder="请输入出版社"/>
        </el-form-item>
        <el-form-item label="isbn">
          <el-input type="textarea" rows="1" v-model="form.isbn" autocomplete="off" placeholder="请输入书籍isbn"/>
        </el-form-item>
        <el-form-item label="简介" >
          <el-input type="textarea" v-model="form.description" rows="6" autocomplete="off" placeholder="请输入简介"/>
        </el-form-item>
        <el-form-item label="分类">
          <el-input type="textarea" rows="1" v-model="form.category" autocomplete="off" placeholder="请输入分类"/>
        </el-form-item>


        <!--上传书籍封面图片-->
        <el-upload
            style="text-align: center"
            ref="uploadpic"
            class="avatar-uploader"
            action="http://61.171.68.199:9091/books/uploadpic"
            :show-file-list="false"
            :auto-upload="false"
            :on-success="addBookInfo"
            :on-change="handlePictureCardPreview"
        >
          <img v-if="form.avatarurl" :src="form.avatarurl" class="avatar" />

          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          <el-dialog :visible.sync="dialogVisible" append-to-body>
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-upload>


        <el-form-item>
          <el-upload
              type="file"
              ref="upload"
              :data="uploadData"
              name="file"
              class="upload-demo"
              action="http://61.171.68.199:9091/books/upload"
              :limit="1"
              :auto-upload="false"
              :on-change="loadJsonFromFile"
              :on-success="uploadPicFile"
              :on-error="uploadError"
              :before-upload="beforeUpload"
              :before-remove="beforeRemove"
          >
            <template #trigger>
              <el-button type="primary" class="ml-5" style="margin: 20px 60px auto" >选择</el-button>
            </template>
            <el-button class="ml-5" type="success" style="margin-bottom: 20px" @click="uploadBookFile">
              确定
            </el-button>
            <template #tip>
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
      uploadData: {},
      dialogVisible: false,
      dialogImageUrl: "",
      queryParams:"",
      user: localStorage.getItem("loguserinfo") ? JSON.parse(localStorage.getItem("loguserinfo")) : {}
    }
  },
  methods:{
    btKeyUp() {
      // 不允许输入中文
      // this.form.bookname= this.form.bookname.replace(/[\u4e00-\u9fa5]/ig,'')
      // 不允许输入特殊符号
      this.form.bookname= this.form.bookname.replace(/[^\w\u4E00-\u9FA5]/g, '')

    },
    handlePictureCardPreview(file) {
      this.dialogVisible = true
      if (!file.url) {
        file.url = URL.createObjectURL(file.raw);
      }
      this.dialogImageUrl = file.url
      this.form.avatarurl = file.url
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
    updatebookpic(respon){
      console.log(respon);
      this.$message.success(respon.msg);
    },

    uploadError(){
      this.$message.error("文件上传失败")
    },
    //先上传书籍文件，手动上传
    uploadBookFile() {
      this.$refs.upload.submit()
    },
    //手动上传书籍文件之后，将书籍的封面上传至服务器
    uploadPicFile(respon){
      console.log(respon);
      this.$refs.uploadpic.submit()
    },
    //将书籍封面上传至服务器之后，在数据库中添加相关的数据
    addBookInfo(respon){
      let file = this.uploadFiles[0]
      // this.$message.error("获取文件名："+file.name)
      this.$message.error("获取信息：" + file.name)
      console.log(file.name);
      console.log(this.form);

      // var filename_=encodeURL(file.name);
      // var bookname_=encodeURL(this.form.bookname);
      // var author_=encodeURL(this.form.author);
      // var publisher_=encodeURL(this.form.publisher);
      // var isbn_=encodeURL(this.form.isbn);
      // var description_=encodeURL(this.form.description);
      // var category_=encodeURL(this.form.category);

      var filename_ = encodeURIComponent(file.name);
      var bookname_ = encodeURIComponent(this.form.bookname);
      var author_ = encodeURIComponent(this.form.author);
      var publisher_ = encodeURIComponent(this.form.publisher);
      var isbn_ = encodeURIComponent(this.form.isbn);
      var description_ = encodeURIComponent(this.form.description);
      var category_ = encodeURIComponent(this.form.category);



      this.request.get("/books/addbookinfo", {
        params: {
          filename: filename_,
          bookname: bookname_,
          author: author_,
          publisher: publisher_,
          isbn: isbn_,
          description: description_,
          category: category_,
        }
      })
          .then(res => {
            if (res.code === '200') {
              this.$message.success("上传成功")
              //this.$router.push("/admins/ebookmanage")
            } else {
              this.$message.error("上传失败")
            }
          })
    },
    handleAvatarSuccess(res){
      this.form.avatarurl = res
    },
    picChange(file){
      if (!file.url) {
        file.url = URL.createObjectURL(file.raw);
      }
      // this.form.avatarurl = URL.createObjectURL(file.raw);

      this.$message.error("图片预览url"+file.url);
      console.log(file)
      this.form.avatarurl = file.uid
    },
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
