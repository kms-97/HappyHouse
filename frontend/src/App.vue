<template>
  <div id="material-kit">
    <div :class="{ 'nav-open': NavbarStore.showNavbar }">
      <router-view name="header" />
      <div>
        <router-view />
      </div>

      <a href="#" class="gotop">
        <i class="bi bi-caret-up-fill" style="color:#044712;">top</i></a
      >
      <router-view name="footer" />
    </div>
  </div>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
/* MapDetailList 테이블 관련 설정. */
.md-tabs-content table thead {
  display: table-header-group !important;
}

.md-table-head-label {
  padding-left: 24px !important;
}
.gotop {
  z-index: 100;
  position: fixed;
  width: 14px;
  height: 3em;
  bottom: 1em;
  right: 3em;
  display: flex;
  justify-content: center;
  align-items: center;
  text-decoration: none;
  color: white;
}

.placeinfo_wrap {position:absolute;bottom:28px;left:-150px;width:300px;}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;padding-bottom: 10px;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}
.placeinfo_wrap .after {content:'';position:relative;margin-left:-12px;left:50%;width:22px;height:12px;background:url('https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.placeinfo a, .placeinfo a:hover, .placeinfo a:active{color:#fff !important;text-decoration: none !important;}
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {font-weight: bold; font-size:14px;border-radius: 6px 6px 0 0;margin: -1px -1px 0 -1px;padding:10px; color: #fff;background: #344B26;background: #344B26 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px center;}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>
