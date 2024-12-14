import React from 'react'

export type Props = {
    banners: string[];
    title: string
}

const Slider = ({ banners, title }: Props) => {
    return (
        <section className="hero-section hero-section-full-height">
            <div className="container-fluid">
                <div className="row">

                    <div className="col-lg-12 col-12 p-0">
                        <div id="hero-slide" className="carousel carousel-fade slide" data-bs-ride="carousel">
                            <div className="carousel-inner">
                                {
                                    banners.map((banner, index) =>
                                        <div className={`carousel-item ${index === 0 ? "active" : ""}`}>
                                            <img src={banner}
                                                className="carousel-image img-fluid" alt="..." />

                                            <div className="carousel-caption d-flex flex-column justify-content-end">
                                                <h1>Unicharity</h1>
                                                <p>{title}</p>
                                            </div>
                                        </div>
                                    )
                                }
                            </div>

                            <button className="carousel-control-prev" type="button" data-bs-target="#hero-slide"
                                data-bs-slide="prev">
                                <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span className="visually-hidden">Previous</span>
                            </button>

                            <button className="carousel-control-next" type="button" data-bs-target="#hero-slide"
                                data-bs-slide="next">
                                <span className="carousel-control-next-icon" aria-hidden="true"></span>
                                <span className="visually-hidden">Next</span>
                            </button>
                        </div>
                    </div>

                </div>
            </div>
        </section>
    )
}

export default Slider
