<template>
    <div class="pdf">
        <iframe
        :src="`/lib/pdfjs-3.5.141-dist/web/viewer.html?file=${encodeURIComponent(url)}`"
        frameborder="0"
        style="width: 100%; height: calc(100vh)"
        ></iframe>
        <h1>Hello, World!</h1>
    </div>
</template>

<script>
export default {
    name : "pdf",
    data() {
        return {
        url: '',
        loading: false,
        };
  },
  created() {
        this.url = this.$route.params.url;
        console.log('Received url', this.url);
  },
  methods: {
        getPdfCode: function () {
        this.loading = true;
        // 数据文件流 转成 pdf
        // 在组件的声明周期钩子函数中获取路由参数
        this.url = this.$route.params.url;
        console.log('Received url', url);
        }
  },
  Mountmounted() {
        this.$nextTick(() => {
        this.getPdfCode();
        });
  },
};
</script>

<style lang="scss" scoped></style>
