import React from "react";
import Chart from "react-apexcharts";
import { ResultChartYear } from "../../Models/CharYear";

interface MonthlyTotalChartProps {
  data: ResultChartYear; // Dữ liệu đầu vào
}

const YearChart: React.FC<MonthlyTotalChartProps> = ({ data }) => {
  // Chuyển đổi dữ liệu từ object thành mảng
  const months = Object.keys(data).map((key) => parseInt(key)); // Các tháng (1, 2, 3, ..., 12)
  const totalAmounts = months.map((month) => data[month]); // Tổng tiền cho từng tháng

  // Chuẩn bị dữ liệu cho ApexCharts
  const chartOptions = {
    chart: {
      id: "monthly-total-chart",
      toolbar: {
        show: false,
      },
    },
    xaxis: {
      categories: months.map((month) => "Tháng " + month), 
      title: {
        text: "Tháng",
      },
    },
    yaxis: {
      title: {
        text: "Tổng Chiến Dịch",
      },
    },
    tooltip: {
      y: {
        formatter: (value: number) => `${value.toLocaleString()}`, 
      },
    },
    colors: ["#008FFB"], 
    title: {
      text: "Tổng tiền theo từng tháng trong năm",
      align: "center",
    },
  };

  const chartSeries = [
    {
      name: "Tổng tiền",
      data: totalAmounts, // Gắn tổng tiền vào cột
    },
  ];

  return (
    <div>
      <Chart options={chartOptions} series={chartSeries} type="bar" height={600} />
    </div>
  );
};

export default YearChart;