import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Bar } from 'react-chartjs-2';

const BarChart = ({ month }) => {
    const [barChartData, setBarChartData] = useState({
        labels: [],
        datasets: [{
            label: 'Number of Items',
            data: [],
            backgroundColor: 'rgba(75, 192, 192, 0.6)'
        }]
    });

    useEffect(() => {
        fetchBarChartData();
    }, [month]);

    const fetchBarChartData = async () => {
        try {
            const response = await axios.get(`/api/bar_chart`, { params: { month } });
            const data = response.data;

            const labels = data.map(item => item.priceRange);
            const counts = data.map(item => item.count);

            setBarChartData({
                labels,
                datasets: [{
                    label: 'Number of Items',
                    data: counts,
                    backgroundColor: 'rgba(75, 192, 192, 0.6)'
                }]
            });
        } catch (error) {
            console.error('Error fetching bar chart data:', error);
        }
    };

    return (
        <div className="mt-5">
            <h3>Transactions Bar Chart</h3>
            <Bar data={barChartData} />
        </div>
    );
};

export default BarChart;
