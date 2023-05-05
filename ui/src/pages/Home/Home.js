import React from "react";
import './Home.css';

const Home = () => {
    return (
        <div style={{backgroundImage: "url(/homeBackgroundPng.png)" }} className='home'>
            <section className='homeContainer' >
                <div className='content'>
                    <h1>Grow your <br/> <span className="content_highlight">Business by</span>
                        <br/>Using <span className="content_highlight">G4 software</span></h1>
                    <p>The perfect solution <br/> for managing your projects.</p>
                </div>
                <img src="/logoHome.svg" alt="Logo of G4"/>
            </section>
        </div>
    )
}

export default Home;