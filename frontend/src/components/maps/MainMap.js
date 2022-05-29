export default class Map {
    constructor({ $target, options }) {
        this.state = {
            markers: [],
            infoWindow: null,
        }
        this.$el = [];
        this.$target = $target;
        this.options = options;
        this.$map = null;
        this.ps = null;

        window.kakao && window.kakao.maps ? this.init() : this.addScript();
    }

    init() {
        kakao.maps.load(() => {
            this.mapOptions = {
                center: new kakao.maps.LatLng(this.options.lat, this.options.lng),
                level: this.options.level,
            }
            // 지도 생성
            this.$map = new kakao.maps.Map(this.$target, this.mapOptions);

            // 장소 검색 객체 생성
            this.ps = new kakao.maps.services.Places(this.$map);

            // 인포윈도우 생성
            this.state.infoWindow = new kakao.maps.InfoWindow({ zIndex: 1 });

            // 확대 축소 기능 설정
            this.setZoomable(this.options.zoomable);

            // 이동 기능 설정
            this.setDraggable(this.options.draggable);
        })
    }

    addScript() {
        const script = document.createElement('script'); /* global kakao */
        script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAOKEY}&libraries=services&autoload=false`;
        script.onload = () => this.init();

        document.head.appendChild(script);
    }

    setZoomable(boolean) {
        this.$map.setZoomable(boolean !== undefined ? boolean : true);
    }

    setDraggable(boolean) {
        this.$map.setDraggable(boolean !== undefined ? boolean : true);
    }

    removeMarker() {
        this.state.markers.forEach(marker => marker.setMap(null));
        this.state.markers.markers = [];
    }

    searchPlaces(category) { // 카테고리 장소 검색
        if (!category) return;

        // 인포윈도우 감추기
        this.placeOverlay.setMap(null);

        this.removeMarker();
        this.ps.categorySearch(category, this.placesSearchCB.bind(this), { useMapBounds: true });
    }

    placesSearchCB(data, status) { // 카테고리 장소 검색 콜백
        if (status === kakao.maps.services.Status.OK) {
            // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
            this.displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
            // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요

        } else if (status === kakao.maps.services.Status.ERROR) {
            // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
    }

    displayPlaces(places) { // 지도에 장소 표시
        const bounds = new kakao.maps.LatLngBounds();
        this.$el = [];

        this.removeMarker();
        places.forEach(place => {
            // 마커가 표시될 위치입니다 
            const placePosition = new kakao.maps.LatLng(place.lat, place.lng);
            const itemEl = this.getListItem(place);
            const title = place.aptName;
            this.$el.push(itemEl);

            // 마커를 생성합니다
            const marker = new kakao.maps.Marker({
                position: placePosition
            });

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            bounds.extend(placePosition);

            kakao.maps.event.addListener(marker, 'mouseover', () => {
                this.displayInfowindow(marker, title);
            });

            kakao.maps.event.addListener(marker, 'mouseout', () => {
                this.state.infoWindow.close();
            });

            itemEl.onmouseover = () => {
                this.displayInfowindow(marker, title);
            };

            itemEl.onmouseout = () => {
                this.state.infoWindow.close();
            };

            marker.setMap(this.$map);
            this.state.markers.push(marker);
        })

        this.$map.setBounds(bounds);
    }

    displayInfowindow(marker, title) {
        const content = `<div style="padding:5px;z-index:1;">${title}</div>`;

        this.state.infoWindow.setContent(content);
        this.state.infoWindow.open(this.$map, marker);
    }

    getListItem(place) {
        const el = document.createElement("md-list-item");
        el.innerHTML = `
            <div class="md-list-item-text aptItem" data-type="apt" data-code=${place.aptCode} data-value=${place.aptName} @click="">
                <span>${place.aptName}</span>
                <span>${place.dongName} ${place.jibun}</span>
            </div>
        `
        return el;
    }

    getEl() {
        return this.$el;
    }
}