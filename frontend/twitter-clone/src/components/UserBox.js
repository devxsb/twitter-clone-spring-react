import React from "react";
import LogoutIcon from '@mui/icons-material/Logout';
import {useDispatch} from "react-redux";
import {logout} from "../redux/reduxSlice";

const UserBox = () => {
    const dispatch = useDispatch()
    const logoutClick = () => {
        dispatch(logout())
    }
    return (
        <div
            className="flex items-center mb-6 hover:bg-primary-light cursor-pointer rounded-full py-2 px-4 transform transition-colors duration-200"
            onClick={() => console.log("sa")}>
            <img
                src="https://pbs.twimg.com/profile_images/1617244452027879425/cODTtPoH_400x400.jpg"
                alt="Profile"
                className="w-11 h-11 rounded-full"
            />
            <div className="flex flex-col ml-3">
                <span className="font-bold text-md text-black">Safa</span>
                <span className="text-sm text-gray-dark">@safalifter</span>
            </div>
            <LogoutIcon style={{marginLeft: "auto"}} onClick={logoutClick}/>
        </div>
    );
};

export default UserBox;
