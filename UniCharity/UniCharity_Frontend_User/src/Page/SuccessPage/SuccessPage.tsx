import React from "react";
import { useLocation, useNavigate } from "react-router-dom";

const PaymentReturn: React.FC = () => {
  const location = useLocation();

  const parseQuery = (query: string) => {
    const params = new URLSearchParams(query);
    const result: { [key: string]: string } = {};
    params.forEach((value, key) => {
      result[key] = value;
    });
    return result;
  };

  const queryParams = parseQuery(location.search);
  const payment = queryParams['paymentGateway'];
  const amount = queryParams['amount'];
  const amountNumber = parseFloat(decodeURIComponent(amount).substring(0, amount.length - 2).replace(/,/g, ''));

  const navigate = useNavigate()

  // Hiển thị thông tin
  return (
    <div>
      <ul>
        {/* {Object.entries(queryParams).map(([key, value]) => (
          <li key={key}>
            <strong>{key}:</strong> {decodeURIComponent(value)}
          </li>
        ))} */}
      </ul>
      <section className="testimonial-section section-padding section-bg">
        <div className="container">
          <div className="row">

            <div className="col-lg-8 col-12 mx-auto">
              <h2 className="mb-lg-3">Cảm ơn bạn đã đóng góp!</h2>

              <div id="testimonial-carousel" className="carousel carousel-fade slide" data-bs-ride="carousel">

                <div className="carousel-inner">
                  <div className="carousel-item active">
                    <div className="carousel-caption">
                      <h4 className="carousel-title">
                        {amountNumber.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' })}
                      </h4>

                      <small className="carousel-name"><span className="carousel-name-title">Phương Thức</span>,{decodeURIComponent(payment)}</small>
                    </div>
                  </div>
                </div>
              </div>
              <button
                onClick={() => navigate("/")}
                className="btn btn-primary bg-dark border-0 px-4" style={{ background: "#5bc1ac" }} >Quay Lại Trang Chính</button>
            </div>
          </div>
        </div>
      </section >
    </div >
  );
};

export default PaymentReturn;
