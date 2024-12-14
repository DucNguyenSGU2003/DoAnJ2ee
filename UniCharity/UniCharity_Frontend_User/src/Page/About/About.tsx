import React from 'react';

const About = () => {
    return (
        <section className="section-padding section-bg" id="section_2">
            <div className="container">
                <div className="row">
                    <div className="col-lg-6 col-12 mb-5 mb-lg-0">
                        <img
                            src="images/group-people-volunteering-foodbank-poor-people.jpg"
                            className="custom-text-box-image img-fluid"
                            alt="Sinh viên Đại học Sài Gòn"
                        />
                    </div>

                    <div className="col-lg-6 col-12">
                        <div className="custom-text-box">
                            <h2 className="mb-2">Về Chúng Tôi</h2>

                            <h5 className="mb-3">Quỹ Hỗ Trợ Sinh Viên Đại học Sài Gòn</h5>

                            <p className="mb-0">
                                Với sứ mệnh nâng cao chất lượng giáo dục và hỗ trợ sinh viên vượt qua những khó khăn trong học tập,
                                Quỹ Hỗ Trợ Sinh Viên Đại học Sài Gòn ra đời nhằm tạo cơ hội cho các bạn sinh viên phát triển toàn diện
                                về tri thức, kỹ năng và thái độ.
                            </p>
                        </div>

                        <div className="row">
                            <div className="col-lg-6 col-md-6 col-12">
                                <div className="custom-text-box mb-lg-0">
                                    <h5 className="mb-3">Sứ Mệnh</h5>

                                    <p>
                                        Chúng tôi không ngừng tạo điều kiện để sinh viên tiếp cận giáo dục chất lượng
                                        và phát triển trong môi trường năng động.
                                    </p>

                                    <ul className="custom-list mt-2">
                                        <li className="custom-list-item d-flex">
                                            <i className="bi-check custom-text-box-icon me-2"></i>
                                            Sinh Viên Nghèo Vượt Khó
                                        </li>

                                        <li className="custom-list-item d-flex">
                                            <i className="bi-check custom-text-box-icon me-2"></i>
                                            Sinh Viên Có Hoàn Cảnh Khó Khăn
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div className="col-lg-6 col-md-6 col-12">
                                <div className="custom-text-box d-flex flex-wrap d-lg-block mb-lg-0">
                                    <div className="counter-thumb">
                                        <div className="d-flex">
                                            <span className="counter-number" data-from="1" data-to="2003" data-speed="1000"></span>
                                            <span className="counter-number-text"></span>
                                        </div>

                                        <span className="counter-text">Năm Thành Lập</span>
                                    </div>

                                    <div className="counter-thumb mt-4">
                                        <div className="d-flex">
                                            <span className="counter-number" data-from="1" data-to="5000" data-speed="1000"></span>
                                            <span className="counter-number-text">+</span>
                                        </div>

                                        <span className="counter-text">Sinh Viên Được Hỗ Trợ</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
};

export default About;
