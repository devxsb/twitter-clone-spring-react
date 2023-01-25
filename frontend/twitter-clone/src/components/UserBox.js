import React from "react";

const UserBox = () => {
    return (
        <div
            className="flex items-center mb-6 hover:bg-primary-light cursor-pointer rounded-full py-2 px-4 transform transition-colors duration-200">
            <img
                src="https://pbs.twimg.com/profile_images/1617244452027879425/cODTtPoH_400x400.jpg"
                alt="Profile"
                className="w-11 h-11 rounded-full"
            />
            <div className="flex flex-col ml-3">
                <span className="font-bold text-md text-black">Safa</span>
                <span className="text-sm text-gray-dark">@safalifter</span>
            </div>

            <div className="flex space-x-1 ml-auto">
                <div className="w-1 h-1 bg-gray-900 rounded-full"/>
                <div className="w-1 h-1 bg-gray-900 rounded-full"/>
                <div className="w-1 h-1 bg-gray-900 rounded-full"/>
            </div>
        </div>
    );
};

export default UserBox;
