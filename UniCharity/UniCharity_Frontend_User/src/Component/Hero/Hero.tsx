import React from 'react'

const Hero = () => {
  return (
    <section className="hero-section hero-section-full-height">
    <div className="container-fluid">
        <div className="row">

            <div className="col-lg-12 col-12 p-0">
                <div id="hero-slide" className="carousel carousel-fade slide" data-bs-ride="carousel">
                    <div className="carousel-inner">
                        <div className="carousel-item active">
                            <img src="images/slide/volunteer-helping-with-donation-box.jpg"
                                className="carousel-image img-fluid" alt="..."/>

                            <div className="carousel-caption d-flex flex-column justify-content-end">
                                <h1>be a Kind Heart</h1>

                                <p>Professional charity theme based on Bootstrap 5.2.2</p>
                            </div>
                        </div>

                        <div className="carousel-item">
                            <img src="images/slide/volunteer-selecting-organizing-clothes-donations-charity.jpg"
                                className="carousel-image img-fluid" alt="..."/>

                            <div className="carousel-caption d-flex flex-column justify-content-end">
                                <h1>Non-profit</h1>

                                <p>You can support us to grow more</p>
                            </div>
                        </div>

                        <div className="carousel-item">
                            <img src="images/slide/medium-shot-people-collecting-donations.jpg"
                                className="carousel-image img-fluid" alt="..."/>

                            <div className="carousel-caption d-flex flex-column justify-content-end">
                                <h1>Humanity</h1>

                                <p>Please tell your friends about our website</p>
                            </div>
                        </div>
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

export default Hero
