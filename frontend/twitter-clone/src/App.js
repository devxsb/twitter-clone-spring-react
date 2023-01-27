import React from "react";
import Container from "./layout/Container";
import Sidebar from "./layout/Sidebar";
import Content from "./layout/Content";
import Widgets from "./layout/Widgets";
import Login from "./layout/Login";

const App = () => {
    const currentUser = 1
    return (
        <>
            {!currentUser ?
                <Login/> :
                <Container>
                    <Sidebar/>
                    <Content/>
                    <Widgets/>
                </Container>}
        </>
    );
};

export default App;
