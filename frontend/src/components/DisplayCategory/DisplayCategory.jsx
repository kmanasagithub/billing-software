import './DisplayCategory.css';
import Category from '../Category/Category.jsx';
import React from 'react';
import { assets } from '../../assets/assets.js';

const DisplayCategory = ({
    selectedCategory,
    setSelectedCategory,
    categories
}) => {

    const totalItems = categories.reduce(
        (acc, cat) => acc + cat.items,
        0
    );

    return (
        <div className="row g-3">

            {/* All Items */}
            <div className="col-md-3 col-sm-6">
                <Category
                    categoryName="All Items"
                    imgUrl={assets.logo}
                    numberOfItems={totalItems}
                    bgColor="#6c757d"
                    isSelected={selectedCategory === ""}
                    onClick={() => setSelectedCategory("")}
                />
            </div>

            {/* Categories */}
            {categories.map(category => (

                <div
                    key={category.categoryId}
                    className="col-md-3 col-sm-6"
                >
                    <Category
                        categoryName={category.name}
                        imgUrl={category.imgUrl}
                        numberOfItems={category.items}
                        bgColor={category.bgColor}
                        isSelected={
                            selectedCategory === category.categoryId
                        }
                        onClick={() =>
                            setSelectedCategory(category.categoryId)
                        }
                    />
                </div>

            ))}

        </div>
    );
};

export default DisplayCategory;