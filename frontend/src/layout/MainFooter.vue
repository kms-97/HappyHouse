<template>
  <footer
    class="footer"
    :class="{ [`footer-${type}`]: type }"
    :data-background-color="backgroundColor"
  >
    <div class="container">
      <nav>
        <ul>
          <li>
            <a href="https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp">
              SSAFY
            </a>
          </li>
          <li>
            <a
              href="https://lab.ssafy.com/bome0214/happyhouse_final_-_12-_-_.git"
            >
              github
            </a>
          </li>
          <li>
            <a href="https://spring-lee.tistory.com/">
              Blog
            </a>
          </li>
        </ul>
      </nav>
      <div class="copyright">&copy; {{ year }}, SSAFY with 김민수 이봄</div>
    </div>
  </footer>
</template>
<script>
export default {
  props: {
    backgroundColor: String,
    type: String,
  },
  data() {
    return {
      year: new Date().getFullYear(),
    };
  },
};
</script>
<style></style>
