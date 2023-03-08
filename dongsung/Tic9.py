# 게임에 관해 안내한다.
def printInfo():
    print("[2-Player Version]")
    print("이 게임은 기존의 틱택토에 임의의 추가 규칙과 플레이 방식을 추가한 게임입니다.")
    print("1. 'O'부터 시작하고, 각자 돌을 두 개씩 두고 난 후 'X'의 턴부터 자신의 돌 상하좌우 위치에 있는 상대 돌을 제거할 수 있다.")
    print("2. 한 플레이어의 한 턴에는 자신의 돌을 두거나 상대 돌을 지우거나 한 가지의 행동만 할 수 있다.")
    print("3. 돌이 제거 되었을 때는 다른 돌이 추가로 제거 된 후 그 자리에 다시 돌이 설치될 수 있다.")
    print("4. 돌이 제거 된 적 있는 자리엔 어떤 돌도 그 자리에선 다시 제거 될 수 없다.")
    print("5. 상대방의 돌을 하나도 남김없이 모두 지울 수는 없다")
    print("선택 가능: \'◎\', 선택 불가능: \'·\', 당장은 불가능: \'!\'으로 표시됩니다.")
    print("이외의 규칙은 3x3의 보드에 한 줄을 완성하는 틱택토 게임의 규칙을 따라갑니다.")
    print("위치 선택은 아래와 같이 키보드의 숫자 키패드를 이용하세요.")
    print("---------")
    print("| 7 8 9 |")
    print("| 4 5 6 |")
    print("| 1 2 3 |")
    print("---------")
    print("게임을 시작합니다.")


# 보드의 내용을 화면에 출력한다.
def drawBoard(tboard, mboard, rboard):
    print("---------------------------------------")
    print("|", tboard[7], tboard[8], tboard[9], "|     ", "|", mboard[7], mboard[8], mboard[9], "|     ", "|",
          rboard[7], rboard[8], rboard[9], "|     ")
    print("|", tboard[4], tboard[5], tboard[6], "|     ", "|", mboard[4], mboard[5], mboard[6], "|     ", "|",
          rboard[4], rboard[5], rboard[6], "|     ")
    print("|", tboard[1], tboard[2], tboard[3], "|     ", "|", mboard[1], mboard[2], mboard[3], "|     ", "|",
          rboard[1], rboard[2], rboard[3], "|     ")
    print("---------------------------------------")
    print("↑현재 보드↑     ↑설치 가능↑      ↑제거 가능↑")


# 플레이어의 턴에 돌의 설치, 제거 여부를 확인한다.
# 제거 가능한 칸이 없는데 제거 함수를 실행해버리면 탈출할 수 없어지므로 이곳에서 제거 가능 여부를 따진다
# 설치 가능한 칸이 없는 경우는 아마 고려하지 않아도 될 듯 싶다
def getPlayerChoice(board, rboard, rlboard, mboard, stone):
    while (True):
        print("…………………………………………………………………………………")
        print("\'" + stone + "\'" + "님의 턴입니다.")
        inData = input("\'" + stone + "\'" + '님, 돌을 설치하려면 m, 제거하시려면 r를 입력해주세요.')
        # 설치 가능할 때
        if inData == 'm' or inData == 'M':
            getPlayerMove(board, mboard, stone)
            break
        # 제거
        elif inData == 'r' or inData == 'R':
            # 아직 제거가 가능한 4턴이 지나지 않음
            if not CanRemove:
                print("돌이 4개가 설치 되었을 때부터 제거가 가능합니다")
            # 십자 모양으로 제거 가능한 돌이 없음
            elif not canRemove(2, 0, stone, 0, rboard):
                print("제거할 수 있는 돌이 없습니다")
            else:
                # 제거 가능할 때
                if numberOfStone(board) > 1 and canRemove(2, 0, stone, 0, rboard):
                    getPlayerRemove(board, rboard, rlboard, mboard, stone)
                    break

                else:
                    print("제거할 수 있는 돌이 없습니다.")
        else:
            print("m이나 r만 입력하실수 있습니다.")


# 상대 플레이어의 돌을 리턴
def otherPlayerStone(stone):
    if stone == 'O':
        return 'X'
    else:
        return 'O'


# 플레이어로부터 설치할 위치를 입력받는다.
def getPlayerMove(board, mboard, stone):
    while True:
        print("…………………………………………………………………………………")
        inData = input("\'" + stone + "\'" + '님, 설치할 위치를 선택하세요. ')
        if len(inData) == 1 and '1' <= inData <= '9':
            pos = int(inData)
            # 선택한 칸이 전 턴에 막 제거된 칸임
            if mboard[pos] == '!':
                print('해당 위치는 이번 턴에 설치가 불가능합니다.')
            else:
                if board[pos] == '-':
                    board[pos] = stone
                    break
        print('잘못 선택하셨습니다. 비어있는 칸을 숫자로 입력하세요.')


# 플레이어로부터 제거할 위치를 입력받는다
def getPlayerRemove(board, rboard, rlboard, mboard, stone):
    global Flag
    while True:
        print("…………………………………………………………………………………")
        inData = input("\'" + stone + "\'" + '님, 제거할 위치를 선택하세요. ')
        if len(inData) == 1 and '1' <= inData <= '9':
            pos = int(inData)
            if board[pos] == otherPlayerStone(stone) and rboard[pos] == '◎' and rlboard[pos] == True:
                board[pos] = '-'
                rlboard[pos] = False
                mboard[pos] = '!'
                rboard[pos] = '!'

                MMMboard(mboard, pos, Flag)
                Flag = False
                break
        print('잘못 입력하셨습니다. 선택한 자리의 돌을 제거할 수 없습니다.')


# Update 내 수식 간략화
def Update_s(case, stone, board):
    if case == 1:
        dstone = otherPlayerStone(stone)
        if board == dstone:
            return "◎"
        else:
            return "·"
    if case == 2:
        dstone = otherPlayerStone(stone)
        if board == dstone:
            return True
        else:
            return False


# 제거가 가능한 십자가 모양 칸을 확인한다
def canRemove(case, i, stone, tboard, Rboard):
    if case == 1:
        match i:
            case 1:
                Rboard[2] = Update_s(case, stone, tboard[2])
                Rboard[4] = Update_s(case, stone, tboard[4])
            case 2:
                Rboard[1] = Update_s(case, stone, tboard[1])
                Rboard[3] = Update_s(case, stone, tboard[3])
                Rboard[5] = Update_s(case, stone, tboard[5])
            case 3:
                Rboard[2] = Update_s(case, stone, tboard[2])
                Rboard[6] = Update_s(case, stone, tboard[6])
            case 4:
                Rboard[1] = Update_s(case, stone, tboard[1])
                Rboard[5] = Update_s(case, stone, tboard[5])
                Rboard[7] = Update_s(case, stone, tboard[7])
            case 5:
                Rboard[2] = Update_s(case, stone, tboard[2])
                Rboard[4] = Update_s(case, stone, tboard[4])
                Rboard[6] = Update_s(case, stone, tboard[6])
                Rboard[8] = Update_s(case, stone, tboard[8])
            case 6:
                Rboard[3] = Update_s(case, stone, tboard[3])
                Rboard[5] = Update_s(case, stone, tboard[5])
                Rboard[9] = Update_s(case, stone, tboard[9])
            case 7:
                Rboard[4] = Update_s(case, stone, tboard[4])
                Rboard[8] = Update_s(case, stone, tboard[8])
            case 8:
                Rboard[5] = Update_s(case, stone, tboard[5])
                Rboard[7] = Update_s(case, stone, tboard[7])
                Rboard[9] = Update_s(case, stone, tboard[9])
            case 9:
                Rboard[8] = Update_s(case, stone, tboard[8])
                Rboard[6] = Update_s(case, stone, tboard[6])
    if case == 2:
        for i in range(1, 10):
            if Rboard[i] == '◎':
                return True
    return False


# 설치, 제거가 가능한 칸을 업데이트한다
def Update(tboard, mboard, rboard, rlboard, stone):
    # m보드 업데이트
    for i in range(1, 10):
        if mboard[i] == '!':
            mboard[i] = '!'
        elif tboard[i] != '-':
            mboard[i] = "·"
        else:
            mboard[i] = "◎"
    # r보드 업데이트
    if CanRemove:
        for i in range(1, 10):
            rboard[i] = "·"

        for i in range(1, 10):
            if tboard[i] == stone:
                for j in range(1, 9):
                    canRemove(1, i, stone, tboard, rboard)

        for i in range(1, 10):
            if not rlboard[i]:
                rboard[i] = '!'


# 보드에 놓여진 돌의 개수를 리턴한다.
def numberOfStone(board):
    n = 0
    for c in board:
        if c != '-':
            n = n + 1
    return n


# mmm보드에 있는 ! 제거하기.
def MMMboard(mboard, pos, Flag):
    for i in range(1, 10):
        if mboard[i] == '!' and i != pos:
            if not Flag:
                mboard[i] = '·'
                Flag = True


# 보드에서 stone('O' 또는 'X')가 연속해서 3개 놓여졌는지 여부(True 또는 False)를 리턴한다.
def isWinner(board, stone):
    # 가로 세 행 확인
    for i in [1, 4, 7]:
        if board[i] == stone and board[i + 1] == stone and board[i + 2] == stone:
            return True

    # 세로 세 열 확인
    for i in [1, 2, 3]:
        if board[i] == stone and board[i + 3] == stone and board[i + 6] == stone:
            return True

    # 대각선(오른쪽 위에서 왼쪽 아래)
    if board[1] == stone and board[5] == stone and board[9] == stone:
        return True

    # 대각선(왼쪽 위에서 오른쪽 아래)
    if board[7] == stone and board[5] == stone and board[3] == stone:
        return True

    return False


Flag = True


# 틱택토 1게임을 진행합니다.
def ticTacToe():
    global CanRemove, OneTime

    currentStone = 'O'  # 먼저 두는 사람이 'O', 뒤에 두는 사람이 'X'를 두는 것으로 정함
    # 빈 틱택토 보드를 표현하기 위해서 '-'로 10개의 요소를 채운 리스트를 만듭니다.
    # 위치 정보와 리스트의 인덱스를 일치시키기 위해 10개의 요소를 채우고, 이후에 맨 앞(인덱스 0) 요소는 사용하지 않습니다.

    # tttboard = 게임이 실행되는 배열
    # mmmboard = 설치 가능한 칸을 표시하는 역할
    # rrrboard = 제거 가능한 칸을 표시하는 역할
    # rrlboard = 설치, 제거 기록을 남기는 역할
    tttBoard = ['-'] * 10
    mmmBoard = ['◎'] * 10
    rrrBoard = ['·'] * 10
    rrlBoard = [True] * 10

    printInfo()

    while True:
        Update(tttBoard, mmmBoard, rrrBoard, rrlBoard, currentStone)
        drawBoard(tttBoard, mmmBoard, rrrBoard)
        getPlayerChoice(tttBoard, rrrBoard, rrlBoard, mmmBoard, currentStone)

        Update(tttBoard, mmmBoard, rrrBoard, rrlBoard, currentStone)

        # 4턴 지났을 때
        if numberOfStone(tttBoard) >= 4:
            CanRemove = True

        if isWinner(tttBoard, currentStone):
            print("'" + currentStone + "'가 이겼습니다.")
            return currentStone

        if numberOfStone(tttBoard) >= 9:
            print("비겼습니다.")
            return '-'

        if currentStone == 'O':
            currentStone = 'X'
            # 4턴이 지나 X에게 연속된 턴을 부여
        elif currentStone == 'X' and CanRemove == True and OneTime == True:
            print("…………………………………………………………………………………")
            print("지금부터 자신의 턴에 돌을 제거 할 수 있습니다.")
            print("\'X\'님부터 시작합니다.")
            currentStone = 'X'
            OneTime = False
            Update(tttBoard, mmmBoard, rrrBoard, rrlBoard, currentStone)
        else:
            currentStone = 'O'


# 게임 결과를 저장할 리스트를 빈 리스트로 생성합니다.
resultList = []
CanRemove = False  # 4턴 지났는지 확인
OneTime = True  # 4턴이 지났을 때 X의 턴을 두번 실행 하게끔 함
Flag = True  # 돌이 제거 된 뒤 다음 제거 턴 확인
while True:
    result = ticTacToe()
    resultList.append(result)

    isContinued = input("한 게임 더 진행할까요?(y/n) ")
    if isContinued == 'n' or isContinued == 'N': break

print('[게임 결과]')
for i in range(len(resultList)):
    print(i + 1, ':', end=' ')
    if resultList[i] == '-':
        print("비김")
    else:
        print(resultList[i], '승')
