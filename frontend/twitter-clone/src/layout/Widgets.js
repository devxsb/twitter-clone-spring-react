import React from "react";
import {Timeline} from "react-twitter-widgets";
import {SearchIcon} from "../icons/Icon";

const Widgets = () => {
    return (
        <aside className="w-1/3">
            <form
                className="flex items-center space-x-4 p-3 m-2 bg-gray-200 rounded-full text-gray-dark focus-within:bg-white focus-within:ring-1 focus-within:ring-primary-base focus-within:text-primary-base"
                onSubmit={e => e.preventDefault()}>
                <SearchIcon className="w-5 h-5"/>
                <input
                    type="text"
                    placeholder="Search Twitter"
                    className="placeholder-gray-dark bg-transparent focus:outline-none w-full text-sm"
                />
            </form>
            <div className="mt-2 px-2">
                <Timeline
                    dataSource={{
                        sourceType: "profile",
                        screenName: "Galatasaray"
                    }}
                    options={{
                        height: "1000"
                    }}
                />
            </div>
        </aside>
    );
};

export default Widgets;
