<template>
  <div>
    <div>这里是图书详情模板组件!</div>

    <el-upload
        class="upload-demo"
        action="http://localhost:9090/file/upload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="100"
        :on-exceed="handleExceed"
        :file-list="fileList">
      <el-button size="small" type="primary">Click to upload</el-button>

      <div slot="tip" class="el-upload__tip">jpg/png files with a size less than 500kb</div>
    </el-upload>

    <a href="D:\Desktop\uploadBooks\b8d218cf997144cb9365e0985dbecede.pdf">预览</a>

    <br>
    <el-button type="primary" style="margin-top: 10px; margin-bottom: 10px" @click="downLoad">预览</el-button>

  </div>
</template>

<script>
export default {
  name: "BookDetails",
  data(){
    return {
      fileList: [{name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}, {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}]
    }
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`The limit is 3, you selected ${files.length} files this time, add up to ${files.length + fileList.length} totally`);
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`Cancel the transfert of ${ file.name } ?`);
    },
    downLoad(){
      let url = 'http://localhost:9090/file/4ca86bd15a0d4298a321679f532177bd.pdf';
      window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${url}`);
    }
  }
}
</script>

<style scoped>

</style>
