export default class Map {
    constructor({ $target, options }) {
        this.state = {
            mainMarker: [],
            markers: [],
            infoWindow: document.createElement('div'),
            order: 0,
        }
        this.placeOverlay = null;
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
            this.$map= new kakao.maps.Map(this.$target, this.mapOptions);

            // 장소 검색 객체 생성
            this.ps = new kakao.maps.services.Places(this.$map);

            // 확대 축소 기능 설정
            this.setZoomable(this.options.zoomable);

            // 이동 기능 설정
            this.setDraggable(this.options.draggable);

            // 인포윈도우 설정
            this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
            this.placeOverlay.setContent(this.state.infoWindow);

            // 인포윈도우 생성
            this.state.infoWindow.className = "placeinfo_wrap";

            //kakao.maps.event.addListener(this.state.infoWindow, 'mousedown', kakao.maps.event.preventMap);
            //kakao.maps.event.addListener(this.state.infoWindow, 'touchstart', kakao.maps.event.preventMap);
            
            //아파트 위치 마커 생성
            this.addMarker({
                position: this.mapOptions.center,
                order: 9,
                type: this.state.mainMarker,
            })
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

    searchPlaces(category, order) { // 카테고리 장소 검색
        if (!category) return;

        // 인포윈도우 감추기
        this.placeOverlay.setMap(null);

        // sprite 위치 저장
        this.state.order = order;
        
        this.removeMarker();
        this.ps.categorySearch(category, this.placesSearchCB.bind(this), {useMapBounds: true});
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
        places.forEach(place => {
            // 마커가 표시될 위치입니다 
            const markerPosition  = new kakao.maps.LatLng(place.y, place.x); 

            // 마커를 생성합니다
            const marker = this.addMarker({
                position: markerPosition,
                order: this.state.order,
                type: this.state.markers,
            });

            kakao.maps.event.addListener(marker, "click", () => {
                this.displayPlaceInfo(place);
            })
        })
    }

    addMarker({ position, order, type }) {
        const imageSrc = require('@/img/map_markers.png'); // 마커 이미지 url, 스프라이트 이미지를 씁니다
        const imageSize = new kakao.maps.Size(48, 48);  // 마커 이미지의 크기\
        const imgOptions =  {
                spriteSize : new kakao.maps.Size(192, 144), // 스프라이트 이미지의 크기
                spriteOrigin : new kakao.maps.Point(48 * (order % 4), 48 * Math.floor(order / 4)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                offset: new kakao.maps.Point(24, 48) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
            };
        const markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);
        const marker = new kakao.maps.Marker({
                position: position, // 마커의 위치
                image: markerImage
            });

        marker.setMap(this.$map); // 지도 위에 마커를 표출합니다
        type.push(marker);  // 배열에 생성된 마커를 추가합니다
    
        return marker;
    }

    displayPlaceInfo (place) {
        const content = `
        <div class="placeinfo">
            <a class="title" href="${place.place_url}" target="_blank" title="${place.place_name}"> ${place.place_name} </a>
            ${place.road_address_name ?
                `
                <span title="${place.road_address_name}">${place.road_address_name}</span>
                <span class="jibun" title="${place.address_name}">(지번 : '${place.address_name}')</span>
                `
                :`<span title="${place.address_name}">${place.address_name}</span>`
            }
                <span class="tel">${place.phone}</span>
        </div><div class="after"></div>
        `
    
        this.state.infoWindow.innerHTML = content;
        this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        this.placeOverlay.setMap(this.$map);  
    }
}