export default class Pagination {
    constructor(data, perPage) {
        this.data = [...data];
        this.perPage = perPage;
        this.length = data.length;
    }

    getTotalPage() {
        return Math.ceil(this.length / this.perPage);
    }

    getData(page) {
        const start = (page - 1) * this.perPage;
        const end = page * this.perPage;

        return this.data.slice(start, end);
    }
}