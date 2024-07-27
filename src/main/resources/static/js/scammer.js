document.addEventListener('DOMContentLoaded', function () {
    // 삭제 버튼 이벤트 리스너 추가
    const deleteButtons = document.querySelectorAll('#delete-btn');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function () {
            const row = button.closest('tr');
            const scammerId = row.querySelector('input[type="hidden"]').value;

            if (confirm('정말 삭제하시겠습니까?')) {
                fetch(`/scammers/delete/${scammerId}`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
                    }
                })
                    .then(response => {
                        if (response.ok) {
                            row.remove();
                        } else {
                            alert('삭제하는 동안 오류가 발생했습니다.');
                        }
                    })
                    .catch(error => {
                        console.error('Error:', error);
                        alert('삭제하는 동안 오류가 발생했습니다.');
                    });
            }
        });
    });

    // 사기꾼 등록 폼의 서밋 이벤트 리스너 추가
    const scammerForm = document.getElementById('scammerForm');
    scammerForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const account = document.getElementById('account').value;

        // 계좌번호 중복 확인
        fetch(`/scammers/check-duplicate?account=${account}`)
            .then(response => response.json())
            .then(data => {
                if (data.exists) {
                    alert(`동일한 계좌번호가 이미 등록되어 있습니다: ${account}`);
                    window.location.href = `/scammers/search?searchType=account&keyword=${account}`;
                } else {
                    // 중복 계좌번호가 없는 경우 폼 제출
                    scammerForm.submit();
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('계좌번호 확인 중 오류가 발생했습니다.');
            });
    });
});
