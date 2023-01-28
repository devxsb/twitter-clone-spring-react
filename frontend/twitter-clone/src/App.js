import React from "react";
import Container from "./layout/Container";
import Sidebar from "./layout/Sidebar";
import Content from "./layout/Content";
import Widgets from "./layout/Widgets";
import Login from "./layout/Login";
import {useSelector} from "react-redux";

const App = () => {
    const currentUser = useSelector(state => state.reduxSlice.currentUser)
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
