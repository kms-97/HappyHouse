<template>
  <div>
    <div>
      <b-tabs content-class="mt-3" justified>
        <b-tab title="거래건수">
          <div>
            <BarChartGenerator
              :chart-options="BarChartOptions"
              :chart-data="BarChartData"
              chart-id="bar-chart"
              dataset-id-key="label"
              :plugins="plugins"
              css-classes=""
              :styles="{}"
              :width="width"
              :height="height"
            />
          </div>
        </b-tab>
        <b-tab title="거래금액">
          <div>
            <LineChartGenerator
              :chart-options="LineChartOptions"
              :chart-data="LineChartData"
              chart-id="line-chart"
              dataset-id-key="label"
              :plugins="plugins"
              css-classes=""
              :styles="{}"
              :width="width"
              :height="height"
            />
          </div>
        </b-tab>
      </b-tabs>
    </div>
    <md-table id="test" v-model="rd" md-sort="dealDate" md-sort-order="desc">
      <md-table-toolbar>
        <h1 class="md-title">Users</h1>
      </md-table-toolbar>

      <md-table-row slot="md-table-row" slot-scope="{ item }">
        <md-table-cell md-label="거래일시" md-sort-by="dealDate">{{
          item.dealDate
        }}</md-table-cell>
        <md-table-cell md-label="거래금액" md-sort-by="dealAmout">{{
          item.dealAmout
        }}</md-table-cell>
        <md-table-cell md-label="층" md-sort-by="floor">{{
          item.floor
        }}</md-table-cell>
      </md-table-row>
    </md-table>
    <md-button class="md-icon-button" @click="getDeals" v-if="page < totalPage">
      <md-icon>keyboard_arrow_down</md-icon>
    </md-button>
    <!-- </div> -->
  </div>
</template>

<script>
import {
  Line as LineChartGenerator,
  Bar as BarChartGenerator,
} from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Filler,
} from "chart.js";
import Pagination from "@/components/Pagination.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Filler
);

export default {
  components: { LineChartGenerator, BarChartGenerator },
  props: {
    deals: {
      type: Array,
      default: [
        {
          aptCode: null,
          dealYear: "",
          dealMonth: "",
          dealDay: "",
          area: "",
          floor: "",
          dealAmout: "",
        },
      ],
    },
  },
  data() {
    return {
      d: [],
      rd: [],
      pagination: null,
      page: 1,
      totalPage: 1,

      datas: new Map(),

      // chart config
      plugins: [],
      height: 400,
      width: 1000,

      // LineChart
      LineChartOptions: {
        responsive: false,
        maintainAspectRatio: false,
      },
      LineChartData: {},

      // BarChart
      BarChartOptions: {
        responsive: false,
        maintainAspectRatio: false,
        scales: {
          y: {
            suggestedMax: 5,
            ticks: {
              stepSize: 1,
            },
          },
        },
      },
      BarChartData: {},
    };
  },
  methods: {
    getMaxMinAvg(datas) {
      const costs = datas.map((data) =>
        parseInt(data.dealAmout.replace(",", ""))
      );
      const sum = costs.reduce((p, c, i) => p + c, 0);
      const avg = (sum / costs.length).toFixed(2);

      return [Math.max(...costs), Math.min(...costs), avg];
    },
    getLabels() {
      return Array.from(this.datas.keys());
    },
    getLength() {
      return Array.from(this.datas.values()).map((data) => data.length);
    },
    getLineDatasets() {
      const sets = [
        {
          label: "최고",
          data: [],
          backgroundColor: `rgba(45, 168, 154, 0.4)`,
          borderColor: `rgba(45, 168, 154, 0.4)`,
        },
        {
          label: "최소",
          data: [],
          backgroundColor: `rgba(45, 168, 154, 0.4)`,
          borderColor: `rgba(45, 168, 154, 0.4)`,
          fill: "-1",
        },
        {
          label: "평균",
          data: [],
          backgroundColor: `rgba(0, 0, 0, 0.8)`,
          borderColor: `rgba(0, 0, 0, 0.8)`,
        },
      ];

      Array.from(this.datas.values()).forEach((data) => {
        const [max, min, avg] = this.getMaxMinAvg(data);
        sets[0].data.push(max);
        sets[1].data.push(min);
        sets[2].data.push(avg);
      });

      return sets;
    },
    getBarDatasets() {
      const sets = [
        {
          label: "거래건",
          data: [],
          backgroundColor: `rgba(45, 168, 154, 0.9)`,
        },
      ];

      sets[0].data = [...this.getLength()];
      return sets;
    },
    getDeals() {
      this.page += 1;
      this.rd = [...this.rd, ...this.pagination.getData(this.page)];
    },
  },
  mounted() {
    //this.d = [...this.deals];
    this.d = this.deals
      .map((el) => ({
        ...el,
        dealDate: `${el.dealYear}-${el.dealMonth.padStart(
          2,
          0
        )}-${el.dealDay.padStart(2, 0)}`,
      }))
      .reverse();
    this.pagination = new Pagination(this.d, 5);
    this.totalPage = this.pagination.getTotalPage();
    this.rd = [...this.rd, ...this.pagination.getData(this.page)];

    this.deals.forEach((deal) => {
      const date = `${deal.dealYear}-${deal.dealMonth}`;

      if (this.datas.has(date)) this.datas.get(date).push(deal);
      else this.datas.set(date, [deal]);
    });

    this.LineChartData = {
      labels: this.getLabels(),
      datasets: this.getLineDatasets(),
    };
    this.BarChartData = {
      labels: this.getLabels(),
      datasets: this.getBarDatasets(),
    };
  },
};
</script>

<style>
p {
  font-size: 16px;
}
.md-layout {
  /* margin: 0 30px; */
  padding: 0 30px;
}
</style>

<style lang="scss" scoped>
.md-ripple {
  font-size: 20px;
}
</style>
