import { useState } from "react";
import { streamCategoryData, postCategoryData } from "../data/SidebarData";
import { GoChevronLeft, GoChevronDown } from "react-icons/go";

function Sidebar() {
    const [expandedIndex, setExpandedIndex] = useState(-1);

    const handleLabelClick = (clickedIndex) => {
        setExpandedIndex((currentExpandedIndex) => {
            if (currentExpandedIndex === clickedIndex) {
                return -1;
            }
            return clickedIndex;
        });
    };

    const renderedStreamCategories = streamCategoryData.map((postCategory, index) => {
        const expanded = index === expandedIndex;
        const icon = (<span>{expanded ? <GoChevronDown /> : <GoChevronLeft />}</span>);

        const renderedPostCategories = postCategoryData.map((postCategory) => {
            
            return(
                <div className="border-b p-5" key={postCategory.id}>
                    <div className="cursor-pointer">{postCategory.label}</div>
                </div>
            );
        });

        return(
            <div key={postCategory.id}>
                <div className="flex items-center justify-between bg-gray-300 p-3 border-b cursor-pointer" onClick={() => handleLabelClick(index)}>{postCategory.label}{icon}</div>
                {expanded && (
                    <div>
                        {renderedPostCategories}
                    </div>
                )}
            </div>
        );
    });

    return(
        <div className="w-1/4">
            {renderedStreamCategories}
        </div>
    );
}

export default Sidebar;