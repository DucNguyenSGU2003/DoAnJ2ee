import React, { useEffect, useState } from 'react';
import ReactApexChart from 'react-apexcharts';

type Props = {
  data: [number, string, number][]; // Dữ liệu đầu vào kiểu mảng các mảng có 3 phần tử
};

const BarChartTop = ({ data }: Props) => {
  const [labels, setLabels] = useState<string[]>([]);  // Lưu trữ labels (ID và Name)
  const [datas, setDatas] = useState<number[]>([]);    // Lưu trữ dữ liệu (value)

  useEffect(() => {
    if (Array.isArray(data) && data.length > 0) {
      // Chuyển đổi dữ liệu thành định dạng cho biểu đồ
      const newLabels = data.map(item => `${item[0]} - ${item[1]}`);  // Kết hợp ID và Name
      const newDatas = data.map(item => item[2]);  // Lấy giá trị từ phần tử thứ 3 (Value)

      // Cập nhật trạng thái với dữ liệu mới
      setLabels(newLabels);
      setDatas(newDatas);
    }
  }, [data]); // Khi data thay đổi, chạy lại logic

  // Cấu hình cho biểu đồ
  const chartOptions: ApexCharts.ApexOptions = {
    chart: {
      type: 'bar',  // Loại biểu đồ: cột
    },
    plotOptions: {
      bar: {
        horizontal: false,  // Cột theo chiều dọc
        columnWidth: '50%',  // Độ rộng cột
      },
    },
    dataLabels: {
      enabled: false,  // Tắt hiển thị nhãn dữ liệu trên các cột
    },
    xaxis: {
      categories: labels,  // Sử dụng labels cho trục X
    },
    legend: {
      position: 'top',  // Đặt legend (chú thích) ở trên cùng
    },
    responsive: [
      {
        breakpoint: 480,  // Thiết lập cho màn hình nhỏ
        options: {
          chart: {
            width: 300,  // Độ rộng của biểu đồ cho màn hình nhỏ
          },
          legend: {
            position: 'bottom',  // Di chuyển legend xuống dưới cho màn hình nhỏ
          },
        },
      },
    ],
  };

  const chartSeries = [
    {
      name: 'Data',
      data: datas || [],  // Kiểm tra và sử dụng dữ liệu an toàn
    },
  ];

  return (
    <div>
      <h2>Bar Chart Example</h2>
      {datas.length > 0 && labels.length > 0 ? (
        <ReactApexChart
          options={chartOptions}  // Cấu hình biểu đồ
          series={chartSeries}    // Dữ liệu cho biểu đồ
          type="bar"              // Loại biểu đồ là cột
          height={500}            // Chiều cao biểu đồ
        />
      ) : (
        <p>No data available for the chart</p>  // Thông báo khi không có dữ liệu
      )}
    </div>
  );
};

export default BarChartTop;
