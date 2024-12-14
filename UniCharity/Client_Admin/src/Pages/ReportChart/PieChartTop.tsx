import React, { useEffect, useState } from 'react';
import ReactApexChart from 'react-apexcharts';
import { ResultTopDontaionByCampaignId } from '../../Models/Donation';

type Props = {
  data: ResultTopDontaionByCampaignId[][];
};

const PieChartTop = ({ data }: Props) => {
  const [labels, setLabels] = useState<string[]>([]);
  const [datas, setDatas] = useState<number[]>([]);

  useEffect(() => {
    if (data && Array.isArray(data)) {
      const newLabels: string[] = [];
      const newDatas: number[] = [];

      // Duyệt qua từng mảng con trong dữ liệu
      data.forEach((item) => {
        // Kiểm tra độ dài của mảng con, đảm bảo có 3 phần tử
        if (item.length === 3) {
          // item[0] là id, item[1] là name, item[2] là value
          newLabels.push(`${item[0]}-${item[1]}`);  // Kết hợp id và name để làm label
          newDatas.push(item[2]);  // Giá trị (value) cho chart series
        }
      });

      // Cập nhật lại dữ liệu vào state
      setLabels(newLabels);
      setDatas(newDatas);
    }
  }, [data]);

  const chartOptions: ApexCharts.ApexOptions = {
    chart: {
      type: 'donut',
    },
    labels: labels,
    responsive: [
      {
        breakpoint: 1000,
        options: {
          chart: {
            width: 1000,
          },
          legend: {
            position: 'bottom',
          },
        },
      },
    ],
    legend: {
      position: 'bottom',
    },
  };

  const chartSeries = datas;

  console.log("data Pie:", data);
  return (
    <div>
      <h2>Sơ đồ tròn người donation nhiều nhất</h2>
      <ReactApexChart
        options={chartOptions}
        series={chartSeries}
        type="donut"
        height={500}
      />
    </div>
  );
};

export default PieChartTop;
