import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Pie } from 'react-chartjs-2';

const PieChart = ({ month }) => {
    const [pieChartData, setPieChartData] = useState({
        labels: [],
        datasets: [{
            label: 'Number of Items',
            data: [],
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                'rgba(75, 192, 192, 0.6)',
                'rgba(153, 102, 255, 0.6)',
                'rgba(255, 159, 64, 0.6)'
            ]
        }]
    });

    useEffect(() => {
        fetchPieChartData();
    }, [month]);

    const fetchPieChartData = async () => {
        try {
            const response = await axios.get(`/api/pie_chart`, { params: { month } });
            const data = response.data;

            const labels = data.map(item => item.category);
            const counts = data.map(item => item.count);

            setPieChartData({
                labels,
                datasets: [{
                    label: 'Number of Items',
                    data: counts,
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)'
                    ]
                }]
            });
        } catch (error) {
            console.error('Error fetching pie chart data:', error);
        }
    };

    return (
        <div className="mt-5">
            <h3>Transactions Pie Chart</h3>
            <Pie data={pieChartData} />
        </div>
    );
};

export default PieChart;
