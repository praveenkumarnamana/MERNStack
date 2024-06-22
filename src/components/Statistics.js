import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Statistics = ({ month }) => {
    const [statistics, setStatistics] = useState({
        totalSaleAmount: 0,
        totalSoldItems: 0,
        totalNotSoldItems: 0
    });

    useEffect(() => {
        fetchStatistics();
    }, [month]);

    const fetchStatistics = async () => {
        try {
            const response = await axios.get(`/api/statistics`, { params: { month } });
            setStatistics(response.data);
        } catch (error) {
            console.error('Error fetching statistics:', error);
        }
    };

    return (
        <div className="row mt-5">
            <div className="col-md-4">
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">Total Sale Amount</h5>
                        <p className="card-text">${statistics.totalSaleAmount.toFixed(2)}</p>
                    </div>
                </div>
            </div>
            <div className="col-md-4">
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">Total Sold Items</h5>
                        <p className="card-text">{statistics.totalSoldItems}</p>
                    </div>
                </div>
            </div>
            <div className="col-md-4">
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">Total Not Sold Items</h5>
                        <p className="card-text">{statistics.totalNotSoldItems}</p>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Statistics;
