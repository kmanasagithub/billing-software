import React, { useContext, useState } from 'react';
import { AppContext } from '../../context/AppContext';
import './CategoryList.css';
import { deleteCategory } from '../../Service/CategoryService';
import toast from 'react-hot-toast';

const CategoryList = () => {
  const { categories, setCategories } = useContext(AppContext);
  const [searchTerm, setSearchTerm] = useState('');

  const filteredCategories = categories.filter((category) =>
    category.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const deleteByCategoryId = async (categoryId) => {
    try {
      const res = await deleteCategory(categoryId);

      if (res.status === 204) {
        const updatedCategories = categories.filter(
          (category) => category.categoryId !== categoryId
        );

        setCategories(updatedCategories);
        toast.success('Category Deleted');
      } else {
        toast.error('Unable to delete the category');
      }
    } catch (error) {
      console.error(error);
      toast.error('Unable to delete the category');
    }
  };

  return (
    <div
      className="category-list-container"
      style={{
        height: '100vh',
        overflowY: 'auto',
        overflowX: 'auto'
      }}
    >

      {/* Search Bar */}
      <div className="row pe-2">
        <div className="input-group mb-3">

          <input
            type="text"
            name="keyword"
            id="keyword"
            placeholder="Search by keyword"
            className="form-control"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />

          <span className="input-group-text bg-warning">
            <i className="bi bi-search"></i>
          </span>

        </div>
      </div>

      {/* Categories */}
      <div className="row g-3">

        {filteredCategories.map((category) => (

          <div key={category.categoryId} className="col-12">

            <div
              className="card p-3"
              style={{ backgroundColor: category.bgColor }}
            >

              <div className="d-flex align-items-center">

                {/* Category Image */}
                <div style={{ marginRight: '15px' }}>
                  <img
                    src={category.imgUrl}
                    alt={category.name}
                    className="category-image"
                  />
                </div>

                {/* Category Details */}
                <div className="flex-grow-1">

                  <h6 className="mb-1 category-name">
                    {category.name}
                  </h6>

                  <p className="mb-0 text-white">
                    {category.items} items
                  </p>

                </div>

                {/* Delete Button */}
                <div>
                  <button
                    className="btn btn-danger btn-small"
                    onClick={() =>
                      deleteByCategoryId(category.categoryId)
                    }
                  >
                    <i className="bi bi-trash"></i>
                  </button>
                </div>

              </div>

            </div>

          </div>

        ))}

      </div>

    </div>
  );
};

export default CategoryList;