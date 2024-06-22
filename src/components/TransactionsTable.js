import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TransactionsTable = ({ month }) => {
    const [transactions, setTransactions] = useState([]);
    const [search, setSearch] = useState('');
    const [page, setPage] = useState(1);
    const [perPage, setPerPage] = useState(10);

    useEffect(() => {
        fetchTransactions();
    }, [month, search, page]);

    const fetchTransactions = async () => {
        try {
            const response = await axios.get(`/api/transactions`, {
                params: { month, search, page, perPage }
            });
            setTransactions(response.data);
        } catch (error) {
            console.error('Error fetching transactions:', error);
        }
    };

    const handleSearchChange = (e) => {
        setSearch(e.target.value);
    };

    const handleNextPage = () => {
        setPage(page + 1);
    };

    const handlePreviousPage = () => {
        setPage(page - 1);
    };

    return (
        <div className="mt-3">
            <input
                type="text"
                className="form-control mb-3"
                placeholder="Search transactions..."
                value={search}
                onChange={handleSearchChange}
            />
            <table className="table table-bordered">
                <thead className="thead-dark">
                    <tr>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Date of Sale</th>
                        <th>Sold</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {transactions.map(transaction => (
                        <tr key={transaction.id}>
                            <td>{transaction.title}</td>
                            <td>{transaction.description}</td>
                            <td>{transaction.price}</td>
                            <td>{new Date(transaction.dateOfSale).toLocaleDateString()}</td>
                            <td>{transaction.sold ? 'Yes' : 'No'}</td>
                            <td>{transaction.category}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <div className="d-flex justify-content-between">
                <button className="btn btn-primary" onClick={handlePreviousPage} disabled={page === 1}>
                    Previous
                </button>
                <button className="btn btn-primary" onClick={handleNextPage}>
                    Next
                </button>
            </div>
        </div>
    );
};

export default TransactionsTable;
