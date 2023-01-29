import React from "react";
import {useSelector} from "react-redux";
import Auth from "./pages/Auth";
import {Route, Routes} from "react-router-dom";
import Login from "./layout/Login";
import Signup from "./layout/Signup";
import Home from "./pages/Home";

const App = () => {
    const currentUser = useSelector(state => state.reduxSlice.currentUser)
    return (
        <Routes>
            <Route path='*' element={currentUser ? <Home/> : <Auth/>}/>
            {!currentUser &&
                <>
                    <Route path='/login' element={<Login/>}/>
                    <Route path='/signup' element={<Signup/>}/>
                </>}
        </Routes>
    );
};

export default App;
