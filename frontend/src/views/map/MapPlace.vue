<template>
  <div class="wrap">
    <h3>위치 및 주변 시설</h3>
    <div class="map" ref="map"></div>
    <div class="about">
      <div id="wrapper">
        <div class="tabs text-center">
          <input hidden type="radio" name="tab-css" id="tab-1" checked />
          <label class="tab-control" for="tab-1">편의시설</label>
          <input hidden type="radio" name="tab-css" id="tab-2" />
          <label class="tab-control" for="tab-2">안전시설</label>
          <input hidden type="radio" name="tab-css" id="tab-3" />
          <label class="tab-control" for="tab-3">학군정보</label>

          <div class="tab-content" ref="buttonContainer">
            <div id="tab-panel-1" class="tab-panel">
              <button
                class="spin circle btn searchButton"
                data-order="1"
                data-category="SW8"
              >
                지하철역
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="0"
                data-category="CS2"
              >
                편의점
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="11"
                data-category="CE7"
              >
                카페
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="6"
                data-category="BK9"
              >
                은행
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="4"
                data-category="PO3"
              >
                관공서
              </button>
            </div>
            <div id="tab-panel-2" class="tab-panel">
              <button
                class="spin circle btn searchButton"
                data-order="2"
                data-category="HP8"
              >
                병원
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="5"
                data-category="PM9"
              >
                약국
              </button>
            </div>
            <div id="tab-panel-3" class="tab-panel">
              <button
                class="spin circle btn searchButton"
                data-order="8"
                data-category="SC4"
              >
                학교
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="3"
                data-category="AC5"
              >
                학원
              </button>
              <button
                class="spin circle btn searchButton"
                data-order="7"
                data-category="PS3"
              >
                어린이집/유치원
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Map from "@/components/maps/Map.js";

export default {
  props: {
    address: String,
    lat: String,
    lng: String,
  },
  data() {
    return {
      $map: null,
    };
  },
  watch: {
    lat() {
      this.initMap();
    },
  },
  methods: {
    initMap() {
      this.$map = new Map({
        $target: this.$refs.map,
        options: {
          lat: this.lat,
          lng: this.lng,
          level: 3,
          draggable: false,
          zoomable: false,
        },
      });
    },
  },

  mounted() {
    const container = this.$refs.buttonContainer;

    container.addEventListener("click", (e) => {
      const btn = e.target.closest(".searchButton");
      if (!btn) return;

      const category = btn.dataset.category;
      const order = btn.dataset.order;
      this.$map.searchPlaces(category, order);
    });
  },
};
</script>

<style lang="scss" scoped>
@import url("https://fonts.googleapis.com/css2?family=Overpass:wght@300;600&display=swap");

*,
*::before,
*::after {
  box-sizing: border-box;
}

[hidden] {
  display: none;
  /* visibility: hidden;
        opacity: 1; */
}
#wrapper {
  display: flex;
  align-items: center;
  height: 30vh;
  max-width: 45rem;
  margin: 0 auto;
}
label {
  margin-bottom: 0;
}
.map {
  width: 100%;
  height: 70vh;
  background-color: #f3f3f3;
}
.tabs {
  width: 100%;
  background-color: white;
  border-radius: 0.5rem;
}

.tab-control {
  display: inline-block;
  border-bottom: 2px solid transparent;
  font-size: 1.25rem;
  padding: 0.6rem 1rem;
  cursor: pointer;
  transition: all 0.25s ease;
  color: #c7c7c7;
}

.tab-control:hover {
  color: #344b26;
}

.tab-content {
  border-top: 1px solid #344b26;
  padding: 1rem;
}

.tab-panel {
  display: none;
}

/* Magic style */
input[type="radio"]:checked + .tab-control {
  font-weight: 600;
  color: #344b26;
  border-bottom-color: #344b26;
}

#tab-1:checked ~ .tab-content > #tab-panel-1 {
  display: block;
}

#tab-2:checked ~ .tab-content > #tab-panel-2 {
  display: block;
}

#tab-3:checked ~ .tab-content > #tab-panel-3 {
  display: block;
}
//Colors
$background: #fefefe;
$text: #4b507a;

$cyan: rgb(14, 84, 56);
$yellow: #fbca67;
$orange: #ff8a30;
$red: #f45e61;
$purple: #6477b9;
$blue: #1a7a07;

// Basic styles
.btn {
  background: none;
  border: 0;
  box-sizing: border-box;
  margin: 1em;
  padding: 1em 2em;

  // Using inset box-shadow instead of border for sizing simplicity
  box-shadow: inset 0 0 0 2px $cyan;
  color: $cyan;
  font-size: inherit;
  font-weight: 700;

  // Required, since we're setting absolute on pseudo-elements
  position: relative;
  vertical-align: middle;

  &::before,
  &::after {
    box-sizing: inherit;
    content: "";
    position: absolute;
    width: 100%;
    height: 100%;
  }
}

.draw {
  transition: color 0.25s;

  &::before,
  &::after {
    // Set border to invisible, so we don't see a 4px border on a 0x0 element before the transition starts
    border: 2px solid transparent;
    width: 0;
    height: 0;
  }

  // This covers the top & right borders (expands right, then down)
  &::before {
    top: 0;
    left: 0;
  }

  // And this the bottom & left borders (expands left, then up)
  &::after {
    bottom: 0;
    right: 0;
  }

  &:hover {
    color: $cyan;
  }

  // Hover styles
  &:hover::before,
  &:hover::after {
    width: 100%;
    height: 100%;
  }

  &:hover::before {
    border-top-color: $cyan; // Make borders visible
    border-right-color: $cyan;
    transition: width 0.25s ease-out,
      // Width expands first
        height 0.25s ease-out 0.25s; // And then height
  }

  &:hover::after {
    border-bottom-color: $cyan; // Make borders visible
    border-left-color: $cyan;
    transition: border-color 0s ease-out 0.5s,
      // Wait for ::before to finish before showing border
        width 0.25s ease-out 0.5s,
      // And then exanding width
        height 0.25s ease-out 0.75s; // And finally height
  }
}

// Inherits from .draw
.meet {
  &:hover {
    color: $yellow;
  }

  // Start ::after in same position as ::before
  &::after {
    top: 0;
    left: 0;
  }

  // Change colors
  &:hover::before {
    border-top-color: $yellow;
    border-right-color: $yellow;
  }

  &:hover::after {
    border-bottom-color: $yellow;
    border-left-color: $yellow;
    transition: height 0.25s ease-out, width 0.25s ease-out 0.25s; // Animate height first, then width height 0.25s ease-out, width 0.25s ease-out 0.25s;
  }
}

// Does not inherit
.center {
  &:hover {
    color: $purple;
  }

  // Set up base styles, we're going to scale instead of animating width/height
  &::before,
  &::after {
    top: 0;
    left: 0;
    height: 100%;
    width: 100%;
    transform-origin: center; // Ensure scaling is done from the center (expands outwards)
  }

  // scale3d(<scale-horizontal>, <scale-vertical>, <scale-depth>);
  &::before {
    border-top: 2px solid $purple;
    border-bottom: 2px solid $purple;
    transform: scale3d(0, 1, 1); // Shrink only width
  }

  &::after {
    border-left: 2px solid $purple;
    border-right: 2px solid $purple;
    transform: scale3d(1, 0, 1); // Shrink only height
  }

  &:hover::before,
  &:hover::after {
    transform: scale3d(1, 1, 1); // Show full-size
    transition: transform 0.5s;
  }
}

// Border spins around element
// ::before holds three borders that appear separately, one at a time
// ::after holds one border that spins around to cover ::before's borders, making their appearance seem smooth

.spin {
  width: 5em;
  height: 5em;
  padding: 0;

  &:hover {
    color: $blue;
  }

  &::before,
  &::after {
    top: 0;
    left: 0;
  }

  &::before {
    border: 2px solid transparent; // We're animating border-color again
  }

  &:hover::before {
    border-top-color: $blue; // Show borders
    border-right-color: $blue;
    border-bottom-color: $blue;

    transition: border-top-color 0.15s linear,
      // Stagger border appearances
        border-right-color 0.15s linear 0.1s,
      border-bottom-color 0.15s linear 0.2s;
  }

  &::after {
    border: 0 solid transparent; // Makes border thinner at the edges? I forgot what I was doing
  }

  &:hover::after {
    border-top: 2px solid $blue; // Shows border
    border-left-width: 2px; // Solid edges, invisible borders
    border-right-width: 2px; // Solid edges, invisible borders
    transform: rotate(270deg); // Rotate around circle
    transition: transform 0.4s linear 0s, border-left-width 0s linear 0.35s; // Solid edge post-rotation
  }
}

.circle {
  border-radius: 100%;
  box-shadow: none;

  &::before,
  &::after {
    border-radius: 100%;
  }
}

.thick {
  color: $red;

  &:hover {
    color: #fff;
    font-weight: 700;
  }

  &::before {
    border: 2.5em solid transparent;
    z-index: -1;
  }

  &::after {
    mix-blend-mode: color-dodge;
    z-index: -1;
  }

  &:hover::before {
    background: $red;
    border-top-color: $red;
    border-right-color: $red;
    border-bottom-color: $red;
    transition: background 0s linear 0.4s, border-top-color 0.15s linear,
      border-right-color 0.15s linear 0.15s,
      border-bottom-color 0.15s linear 0.25s;
  }

  &:hover::after {
    border-top: 2.5em solid $red;
    border-left-width: 2.5em;
    border-right-width: 2.5em;
  }
}

/* Page styling */

.buttons {
  isolation: isolate;
}

h1 {
  font-weight: 300;
  font-size: 2.5em;
}
</style>
