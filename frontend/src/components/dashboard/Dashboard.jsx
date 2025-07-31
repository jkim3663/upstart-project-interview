import React, { useEffect, useState }from 'react';
import './Dashboard.css';
import { PRODUCT_API_URL } from '../../constants/constants';

const Dashboard = () => {
  const productApiUrl = PRODUCT_API_URL;
  const [products, setProducts] = useState([]);

  const fetchAllProducts = async ()=> {
    try {
      const resp = await fetch(productApiUrl);
      if (!resp.status === 200) {
        throw new Error('Failed to fetch products');
      }
      const data = await resp.json();
      setProducts(data);
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  }

  useEffect(() => {
    fetchAllProducts();
  }, []);

  return (
    <div className='container'>
      <div className='header'>
        <div className='header-text'>View Your Favorite Products</div>
      </div>
      <div className='body'>
        <div className='product-table'>
          <table>
            <thead>
              <tr>
                <th>Product Name</th>
                <th>Product Price</th>
                <th>Created At</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
                {products.map((product, idx) => (
                  <tr key={idx}>
                    <td>{product.name}</td>
                    <td>{product.price}</td>
                    <td>{product.createdAt}</td>
                    <td><button>Delete</button></td>
                  </tr>
                ))}
            </tbody>
          </table>
        </div>
      </div>
      <div className='footer'>
        
      </div>
    </div>
  );
};

export default Dashboard;