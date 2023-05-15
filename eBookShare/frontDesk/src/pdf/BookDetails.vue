<template>
  <div>
    <div>这里是图书详情模板组件!</div>
    <br>
    <el-upload
        class="upload-demo"
        action="http://124.71.166.37:9091/file/upload"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        multiple
        :limit="100"
        :on-exceed="handleExceed"
        :file-list="fileList">
      <el-button size="small" type="primary">Click to upload</el-button>

      <div slot="tip" class="el-upload__tip">pdf files</div>
    </el-upload>

    <br>
    <el-button type="primary" style="margin-top: 10px; margin-bottom: 10px" @click="previewBook">预览</el-button>

  </div>
</template>

<script>
export default {
  name: "BookDetails",
  data(){
    return {
      fileList: [
        {name: 'food.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'},
        {name: 'food2.jpeg', url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'}
      ],
      booksDetail: []
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
    previewBook(){
      let url = 'http://localhost:9090/file/1de53068902f460b9c7b3bbe99719460.pdf';
      window.open(`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${url}`);
    }
  }
}
</script>

<style scoped>

</style>
