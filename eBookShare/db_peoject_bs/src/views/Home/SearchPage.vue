<template>
  <div class="search-page">
    <div class="search-box">
      <input type="text" class="search-input" v-model="query" @keyup.enter="search" placeholder="请输入搜索关键词">
      <button class="search-btn" @click="search">搜索</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchPage",
  data() {
    return {
      query: '',
      results: []
    }
  },
  methods: {
    search() {
      // 在这里实现搜索逻辑，查询结果存储在 this.results 中
      console.log("Hello,World");
    }
  }
}
</script>

<style scoped>
.search-page {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
  background-color: #f5f5f5;
}

.search-box {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
}

.search-input {
  width: 400px;
  height: 40px;
  border-radius: 20px;
  border: none;
  outline: none;
  padding: 10px;
  font-size: 16px;
}

.search-btn {
  width: 80px;
  height: 40px;
  background-color: #4285f4;
  color: #fff;
  border: none;
  border-radius: 20px;
  margin-left: 10px;
  cursor: pointer;
  font-size: 16px;
}

</style>
