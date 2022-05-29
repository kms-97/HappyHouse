<!-- https://www.chartjs.org/docs/2.9.4/ -->

<script>
import { Line } from 'vue-chartjs'

export default {
    extends: Line,
    props: [
        "title",
        "data",
        "labels",
        "options",
    ],
    computed: {
        chartData: function () {
            return this.data;
        }
    },
    methods: {
        renderLineChart: function () {
            this.renderChart(
                {
                    labels: this.labels,
                    datasets: [
                        {
                            label: 'Price',
                            data: this.chartData,
                            borderColor: "rgba(0, 0, 0, 0.5)",
                            backgroundColor: "rgba(255, 255, 255, 0)",
                            borderJoinStyle: "miter",
                            fill: false,
                            tension: 0.1,
                        }
                    ]
                },
                this.options,
            );
        }
    },
    watch: {
        data() {
            this.renderLineChart();
        }
    },
    mounted() {
        this.renderLineChart(this.chartData, this.options)
    }
}
</script>